package ru.dsci.gs.services;

import org.springframework.stereotype.Service;

import java.net.http.HttpClient;

@Service
public interface HttpClientService {

    HttpClient getClient();

}
