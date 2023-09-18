package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<JsonPerson> getPersons() {
        return personService.findAll()
                .stream()
                .map(JsonPerson::from)
                .toList();
    }
}
