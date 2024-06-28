package ru.dsci.gs.services;

import org.springframework.stereotype.Service;
import ru.dsci.gs.models.entities.GeoData;
import ru.dsci.gs.models.entities.GeoPoint;

import java.io.IOException;

@Service
public interface GeoDataProvider {

    String getAddress(GeoPoint geoPoint) throws IOException, InterruptedException;

    GeoPoint getGeoPoint(String address) throws IOException, InterruptedException;

    GeoData getGeoData(String simpleAddress) throws IOException, InterruptedException;

    GeoData getGeoData(GeoPoint geoPoint) throws IOException, InterruptedException;

}
