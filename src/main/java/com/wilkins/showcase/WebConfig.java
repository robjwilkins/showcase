package com.wilkins.showcase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class WebConfig implements RestTemplateCustomizer {
    @Override
    public void customize(RestTemplate restTemplate) {
        log.info("Rest template message converters: {}", restTemplate.getMessageConverters());
    }
}
