package com.wilkins.showcase.controllers;

import java.io.Serializable;

public record Greeting(String salutation, String name) implements Serializable {
    public static Greeting of(String salutation, String name) {
        return new Greeting(salutation, name);
    }
}
