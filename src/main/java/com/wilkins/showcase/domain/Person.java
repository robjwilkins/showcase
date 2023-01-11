package com.wilkins.showcase.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "people")
public record Person(@Id String id, String title, String forename, String surname) {
    public static Person of(String title, String forename, String surname) {
        return new Person(null, title, forename, surname);
    }
}
