package com.wilkins.showcase.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.quartz.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static org.quartz.TriggerBuilder.newTrigger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping(path = "/greeting", produces = APPLICATION_JSON_VALUE)
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    private static final String JOB_GROUP = "greeting-jobs";

    private final Scheduler scheduler;

    public GreetingController(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @GetMapping
    public Greeting getGreeting(@RequestParam(name = "salutation", required = false, defaultValue = "hello") String salutationParam,
                                @RequestParam(name = "name", required = false, defaultValue = "world") String nameParam) {

        log.info("A greeting was requested");

        var greeting = Greeting.of(salutationParam, nameParam);

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }

    @PostMapping
    public String postGreeting(@RequestParam(name = "salutation", required = false, defaultValue = "hello") String salutationParam,
                               @RequestParam(name = "name", required = false, defaultValue = "world") String nameParam,
                               @RequestParam(name = "durationMins", required = false, defaultValue = "2") String durationMins) throws SchedulerException {
        var jobDataMap = new JobDataMap();
        jobDataMap.put("salutation", salutationParam);
        jobDataMap.put("name", nameParam);
        jobDataMap.put("greeting", Greeting.of(salutationParam, nameParam));
        var id = UUID.randomUUID().toString();
        var jobDetail = JobBuilder.newJob()
                .ofType(GreetingJob.class)
                .withIdentity(id, JOB_GROUP)
                .withDescription("Send Greeting Job")
                .usingJobData(jobDataMap)
                .requestRecovery()
                .build();
        var startAt = Timestamp.valueOf(LocalDateTime.from(
                        new Date().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime())
                .plusMinutes(1));
        var trigger = newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "greeting-triggers")
                .withDescription("Send Greeting Trigger")
                .startAt(startAt)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
                .build();
        log.info("scheduling job to run in 1 minutes time");
        scheduler.scheduleJob(jobDetail, trigger);
        return id;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGreeting(@PathVariable String id) throws SchedulerException {
        log.info("deleting job: {}", id);
        return scheduler.deleteJob(new JobKey(id, JOB_GROUP)) ? noContent().build() : notFound().build();
    }
}
