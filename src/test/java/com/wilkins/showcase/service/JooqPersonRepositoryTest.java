package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@SpringBootTest
public class JooqPersonRepositoryTest {
    @Autowired
    JooqPersonRepository underTest;

    @Test
    void canFindPersons() {
        assertThat(underTest.findAll())
                .extracting(Person::id, Person::title, Person::forename, Person::surname)
                .contains(tuple("system-user", null, "system", "user"));
    }
}
