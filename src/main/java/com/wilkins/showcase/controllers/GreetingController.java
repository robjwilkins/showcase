package com.wilkins.showcase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public Greeting getGreeting(@RequestParam(name = "salutation", required = false) String salutationParam,
                               @RequestParam(name = "name", required = false) String nameParam) {

        log.info("A greeting was requested");

        Greeting greeting = Greeting.of("hello", "world");

        if (!isEmpty(salutationParam)) {
            greeting = greeting.withSalutation(salutationParam);
        }
        if (!isEmpty(nameParam)) {
            greeting = greeting.withName(nameParam);
        }

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }
}
