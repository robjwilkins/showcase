package com.wilkins.showcase.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class GreatExampleService implements ExampleService {


    private final String prefix;
    private final Supplier<String> supplier;
    private final String joiner;

    public GreatExampleService(@Value("${showcase.id-service.prefix}") String prefix,
                               Supplier<String> supplier,
                               @Value("${showcase.id-service.joiner}") String joiner) {
        this.prefix = prefix;
        this.supplier = supplier;
        this.joiner = joiner;
    }

    @Override
    public String findExampleId() {
        return prefix + joiner + supplier.get();
    }

    @Override
    public Example createExample() {
        String exampleId = findExampleId();
        String suppliedExample = supplier.get();
        return new Example(prefix, suppliedExample, exampleId, List.of(prefix, suppliedExample, exampleId));
    }
}
