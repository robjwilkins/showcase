package com.wilkins.showcase.domain;

import java.io.Serializable;

public record Greeting(String salutation, String name) implements Serializable {
}
