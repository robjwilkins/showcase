package com.wilkins.showcase.domain;

public record Greeting(String salutation, String name) {
    public static Greeting of(String salutation, String name) {
        return new Greeting(salutation, name);
    }
}
