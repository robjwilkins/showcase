package com.wilkins.showcase;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@CucumberContextConfiguration
@SpringBootTest(classes = ShowcaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationStepDefs {
    @LocalServerPort
    int port;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    protected String getBaseUrl() {
        return "http://localhost:" + port;
    }

    protected RestTemplate getRestTemplate() {
        return restTemplateBuilder.build();
    }
}
