package com.wilkins.showcase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OnlineWordChecker implements WordChecker {
    private static final Logger log = LoggerFactory.getLogger(OnlineWordChecker.class);
    private final HttpClient client;

    public OnlineWordChecker(HttpClient client) {
        this.client = client;
    }

    @Override
    public boolean isValid(String word) {
        log.debug("checking word: {}", word);
        try {
            var req = HttpRequest.newBuilder().GET().uri(URI.create("https://api.dictionaryapi.dev/api/v2/entries/en/" + word)).build();
            return client.send(req, HttpResponse.BodyHandlers.ofString()).statusCode() == 200;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
