package com.wilkins.showcase.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RequestMapping("/upload")
@RestController
@Slf4j
public class UploadController {

    @PostMapping(consumes = {APPLICATION_XML_VALUE, "application/xml;charset=UTF-8"})
    public ResponseEntity<Person> uploadXml(@RequestBody Person person) {
        log.info("xml person: {}", person);
        var personWithSalary = new Person(person.firstName(), person.lastName(), BigDecimal.valueOf(123456789000000.0));
        return ResponseEntity.ok(personWithSalary);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> uploadJson(@RequestBody Person person) {
        log.info("json person: {}", person);
        var personWithSalary = new Person(person.firstName(), person.lastName(), BigDecimal.valueOf(123456789.0));
        return ResponseEntity.ok(personWithSalary);
    }
}
