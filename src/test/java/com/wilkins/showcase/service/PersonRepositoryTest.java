package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository underTest;

    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4-bionic");

    @BeforeAll
    static void beforeAll() {
        mongoDBContainer.start();
    }

    @Test
    void canFindEveryone() {
        assertThat(underTest.findAll()).isEmpty();
    }

    @Test
    void canFindEveryoneBySurname() {
        underTest.save(Person.of("Mr", "Homer", "Simpson"));
        underTest.save(Person.of("Master", "Bart", "Simpson"));
        underTest.save(Person.of("Master", "Ralph", "Wigham"));
        assertThat(underTest.findAllBySurnameContainingIgnoreCaseOrderBySurnameAscForenameAsc("SIMP"))
                .extracting(Person::title, Person::forename, Person::surname)
                .containsExactly(
                        tuple("Master", "Bart", "Simpson"),
                        tuple("Mr", "Homer", "Simpson")
                );
        assertThat(underTest.findAllBySurnameContainingIgnoreCaseOrderBySurnameAscForenameAsc("I"))
                .extracting(Person::title, Person::forename, Person::surname)
                .containsExactly(
                        tuple("Master", "Bart", "Simpson"),
                        tuple("Mr", "Homer", "Simpson"),
                        tuple("Master", "Ralph", "Wigham")
                );
    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
}
