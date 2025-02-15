package com.wilkins.showcase.controller;

import com.wilkins.showcase.domain.Greeting;

import java.io.Serializable;

public record JsonGreeting(String salutation, String name) implements Serializable {
    public static JsonGreeting of(String salutation, String name) {
        return new JsonGreeting(salutation, name);
    }

    public Greeting asGreeting() {
        return new Greeting(salutation, name);
    }

    public static JsonGreeting from(Greeting greeting) {
        return new JsonGreeting(greeting.salutation(), greeting.name());
    }
}
