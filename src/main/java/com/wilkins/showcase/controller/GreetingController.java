package com.wilkins.showcase.controller;

import com.wilkins.showcase.DefaultProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    private final DefaultProperties defaultProperties;

    public GreetingController(DefaultProperties defaultProperties) {
        this.defaultProperties = defaultProperties;
    }

    @GetMapping
    public JsonGreeting getGreeting(@RequestParam(name = "salutation", required = false) String salutationParam,
                                    @RequestParam(name = "name", required = false) String nameParam) {

        log.info("A greeting was requested");

        var greeting = JsonGreeting.of(
                ofNullable(salutationParam).orElse(defaultProperties.greeting().salutation()),
                ofNullable(nameParam).orElse(defaultProperties.greeting().name()));

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }
}
