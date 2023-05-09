package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Person;

import java.util.List;

public interface PersonService {
    List<Person> everyoneWithSurname(String surname);
    List<Person> everyone();
}
