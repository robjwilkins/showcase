package com.wilkins.showcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.function.Supplier;

@ServletComponentScan
@SpringBootApplication
public class ShowcaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowcaseApplication.class, args);
    }

    @Bean
    public Supplier<String> randomStringSupplier() {
        return () -> UUID.randomUUID().toString();
    }
}
