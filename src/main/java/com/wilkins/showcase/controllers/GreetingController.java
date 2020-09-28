package com.wilkins.showcase.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@RestController
@RequestMapping(path = "/greeting", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GreetingController {

    private static final String JOB_GROUP = "greeting-jobs";
    private final Scheduler scheduler;

    @GetMapping
    public Greeting getGreeting(@RequestParam(name = "salutation", required = false) String salutationParam,
                                @RequestParam(name = "name", required = false) String nameParam) {

        log.info("A greeting was requested");

        var greeting = aGreeting(salutationParam, nameParam);

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }

    @PostMapping
    public String postGreeting(@RequestParam(name = "salutation", required = false) String salutationParam,
                         @RequestParam(name = "name", required = false) String nameParam) throws SchedulerException {
        var jobDataMap = new JobDataMap();
        jobDataMap.put("salutation", aGreeting(salutationParam, nameParam).getSalutation());
        jobDataMap.put("name", aGreeting(salutationParam, nameParam).getName());
        var id = UUID.randomUUID().toString();
        var jobDetail = JobBuilder.newJob()
                .ofType(GreetingJob.class)
                .withIdentity(id, JOB_GROUP)
                .withDescription("Send Greeting Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
        var trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "greeting-triggers")
                .withDescription("Send Greeting Trigger")
                .startAt(new Date())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        return id;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGreeting(@PathVariable String id) throws SchedulerException {
        log.info("deleting job: {}", id);
        return scheduler.deleteJob(new JobKey(id, JOB_GROUP)) ? noContent().build() : notFound().build();
    }

    private Greeting aGreeting(String salutationParam, String nameParam) {
        var greeting = Greeting.of("hello", "world");

        if (!isEmpty(salutationParam)) {
            greeting = greeting.withSalutation(salutationParam);
        }
        if (!isEmpty(nameParam)) {
            greeting = greeting.withName(nameParam);
        }
        return greeting;
    }
}
