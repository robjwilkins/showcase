package com.wilkins.showcase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingApplicationTest {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    int port;

    @Test
    void canGetGreeting() {
        var restTemplate = restTemplateBuilder.build();
        var url = "http://localhost:%s/greetings".formatted(port);
        var greeting = restTemplate.getForObject(url, String.class);
        assertNotNull(greeting);
    }
}
