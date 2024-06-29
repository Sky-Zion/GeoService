package ru.dsci.gs.services.impl;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.dsci.gs.models.entities.GeoData;
import ru.dsci.gs.models.entities.GeoPoint;
import ru.dsci.gs.services.GeoDataProvider;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Service
public class GeoDataProviderImpl implements GeoDataProvider {

    private final static String DEFAULT_URL_CODEPAGE = "UTF-8";
    private final static String YANDEX_GET_ADDRESS_TEMPLATE = "https://geocode-maps.yandex.ru/1.x/?apikey=%s&geocode=%f,%f&sco=latlong&format=json";
    private final static String YANDEX_GET_GEO_TEMPLATE = "https://geocode-maps.yandex.ru/1.x/?apikey=%s&geocode=%s&format=json";

    private final HttpClient httpClient;

    @Value("${yandex.geocoder.key}")
    private String apiKey;

    private HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> sendGetRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        return getResponse(request);
    }

    @Override
    public String getAddress(GeoPoint geoPoint) throws IOException, InterruptedException {
        return getGeoData(geoPoint).getBasicAddress();
    }

    @Override
    public GeoPoint getGeoPoint(String simpleAddress) throws IOException, InterruptedException {
        return getGeoData(simpleAddress).getGeoPoint();
    }

    private GeoData getGeoDataFromResponse(HttpResponse<String> addressResponse) {
        JSONObject dirtyData = new JSONObject(addressResponse.body())
                .getJSONObject("response")
                .getJSONObject("GeoObjectCollection")
                .getJSONArray("featureMember").getJSONObject(0)
                .getJSONObject("GeoObject");
        String address = dirtyData
                .getJSONObject("metaDataProperty")
                .getJSONObject("GeocoderMetaData")
                .getJSONObject("Address")
                .getString("formatted");
        String point = dirtyData
                .getJSONObject("Point")
                .getString("pos");
        String[] coordinates = point.split(" ");
        return new GeoData(address, new GeoPoint(Double.valueOf(coordinates[0]), Double.valueOf(coordinates[1])));
    }

    @Override
    public GeoData getGeoData(GeoPoint geoPoint) throws IOException, InterruptedException {
        HttpResponse<String> addressResponse = sendGetRequest(String.format(YANDEX_GET_ADDRESS_TEMPLATE, apiKey, geoPoint.getLat(), geoPoint.getLon()));
        return getGeoDataFromResponse(addressResponse);
    }

    @Override
    public GeoData getGeoData(String simpleAddress) throws IOException, InterruptedException {
        HttpResponse<String> addressResponse = sendGetRequest(String.format(YANDEX_GET_GEO_TEMPLATE, apiKey, URLEncoder.encode(simpleAddress, DEFAULT_URL_CODEPAGE)));
        return getGeoDataFromResponse(addressResponse);
    }

}
