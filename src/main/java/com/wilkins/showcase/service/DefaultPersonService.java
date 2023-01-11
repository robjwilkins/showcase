package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record DefaultPersonService(PersonRepository personRepository) implements PersonService {

    @Override
    public List<Person> everyoneWithSurname(String surname) {
        return personRepository.findAllBySurnameContainingIgnoreCaseOrderBySurnameAscForenameAsc(surname);
    }

    @Override
    public List<Person> everyone() {
        return personRepository.findAll();
    }
}
