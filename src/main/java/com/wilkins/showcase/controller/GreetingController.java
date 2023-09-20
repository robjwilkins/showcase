package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greetings")
public record GreetingController(GreetingService greetingService) {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping
    public JsonGreeting getGreeting(@RequestParam(name = "salutation", required = false, defaultValue = "hello") String salutationParam,
                                    @RequestParam(name = "name", required = false, defaultValue = "world") String nameParam) {
        log.info("A greeting was requested");

        var greeting = JsonGreeting.of(salutationParam, nameParam);

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }

    @PostMapping
    public JsonGreeting postGreeting(@RequestParam(name = "salutation", required = false, defaultValue = "hello") String salutationParam,
                                     @RequestParam(name = "name", required = false, defaultValue = "world") String nameParam) {
        log.info("A greeting was requested");

        var greeting = JsonGreeting.of(salutationParam, nameParam);

        greetingService.sendGreeting(salutationParam, nameParam);

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }
}
