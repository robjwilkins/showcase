package com.wilkins.showcase.controllers;

import com.wilkins.showcase.services.Greeter;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record GreetingJob(Greeter greeter) implements Job {

    private static final Logger log = LoggerFactory.getLogger(GreetingJob.class);
    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        log.info("******** {}, {} *************", context.getJobDetail().getKey().getName(),
                 greeter.greet((Greeting) jobDataMap.get("greeting")));
    }
}
