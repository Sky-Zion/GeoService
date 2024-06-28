package ru.dsci.gs.services.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.dsci.gs.services.HttpClientService;

import java.net.http.HttpClient;
import java.time.Duration;

@RequiredArgsConstructor
@Service
public class HttpClientServiceImpl implements HttpClientService {

    @Value("${http.timeout}")
    private int timeout;

    private HttpClient client;

    @PostConstruct
    public void init() {
        client =
                HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_2)
                        .followRedirects(HttpClient.Redirect.NORMAL)
                        .connectTimeout(Duration.ofSeconds(timeout))
                        .build();
    }

    @Override
    public HttpClient getClient() {
        return client;
    }

}
