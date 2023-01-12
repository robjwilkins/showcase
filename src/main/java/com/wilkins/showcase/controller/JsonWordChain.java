package com.wilkins.showcase.controller;

import java.util.List;

public record JsonWordChain(List<String> chain) {
    public static JsonWordChain of(List<String> chain) {
        return new JsonWordChain(List.copyOf(chain));
    }
}
