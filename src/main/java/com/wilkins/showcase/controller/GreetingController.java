package com.wilkins.showcase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping
    public JsonGreeting getGreeting(@RequestParam(name = "salutation", required = false, defaultValue = "hello") String salutationParam,
                                    @RequestParam(name = "name", required = false, defaultValue = "world") String nameParam) {

        log.info("A greeting was requested");

        var greeting = JsonGreeting.of(salutationParam, nameParam);

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }
}
