package com.wilkins.showcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
public class ShowcaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowcaseApplication.class, args);
    }

    @Bean
    HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}
