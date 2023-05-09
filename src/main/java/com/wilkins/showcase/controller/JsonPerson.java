package com.wilkins.showcase.controller;

import com.wilkins.showcase.domain.Person;

public record JsonPerson(String title, String forename, String surname) {
    public static JsonPerson from(Person person) {
        return new JsonPerson(person.title(), person.forename(), person.surname());
    }
}
