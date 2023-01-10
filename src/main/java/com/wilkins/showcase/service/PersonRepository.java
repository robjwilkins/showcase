package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();
}
