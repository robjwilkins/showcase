package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
@Slf4j
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public JsonGreeting getGreeting(@RequestParam(name = "salutation", required = false, defaultValue = "hello") String salutationParam,
                                    @RequestParam(name = "name", required = false, defaultValue = "world") String nameParam) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        log.info("A greeting was requested");

        var greeting = JsonGreeting.of(salutationParam, nameParam);
        greetingService.greet(nameParam, salutationParam);

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }
}
