package com.wilkins.showcase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    private final FeatureManager featureManager;

    private static final Feature cheeky = new NamedFeature("CHEEKY_GREETING");

    public GreetingController(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    @GetMapping
    public JsonGreeting getGreeting(@RequestParam(name = "salutation", required = false, defaultValue = "hello") String salutationParam,
                                    @RequestParam(name = "name", required = false, defaultValue = "world") String nameParam) {

        log.info("A greeting was requested");

        var greeting = JsonGreeting.of(salutationParam, nameParam);
        if (featureManager.isActive(cheeky)) {
            greeting = JsonGreeting.of("Get out of here!", nameParam);
        }

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }
}
