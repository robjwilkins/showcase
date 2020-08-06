package com.wilkins.showcase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class StringyIdService implements IdService {

    private final String prefix;
    private final Supplier<String> randomStringSupplier;
    private final String joiner;

    @Override
    public String stringId() {
        return prefix + joiner + randomStringSupplier.get();
    }

    @Override
    public Identifier identifier() {
        String stringId = stringId();
        String randomString = randomStringSupplier.get();
        return new Identifier(prefix, randomString, stringId, List.of(prefix, randomString, stringId));
    }
}
