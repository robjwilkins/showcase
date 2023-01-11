package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findAllBySurnameContainingIgnoreCaseOrderBySurnameAscForenameAsc(String surname);
}
