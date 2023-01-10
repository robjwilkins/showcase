package com.wilkins.showcase.controller;

import java.io.Serializable;

public record JsonGreeting(String salutation, String name) implements Serializable {
    public static JsonGreeting of(String salutation, String name) {
        return new JsonGreeting(salutation, name);
    }
}
