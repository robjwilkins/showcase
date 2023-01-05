package com.wilkins.showcase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public Greeting getGreeting(@RequestParam(name = "salutation", required = false, defaultValue = "hello") String salutationParam,
                               @RequestParam(name = "name", required = false, defaultValue = "world") String nameParam) {

        log.info("A greeting was requested");

        var greeting = Greeting.of(salutationParam, nameParam);

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }
}
