package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public record PersonController(PersonService personService) {

    @GetMapping
    public List<JsonPerson> getPeople() {
        return personService.everyone()
                .stream()
                .map(JsonPerson::from)
                .toList();
    }
}
