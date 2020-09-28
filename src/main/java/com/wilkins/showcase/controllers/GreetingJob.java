package com.wilkins.showcase.controllers;

import com.wilkins.showcase.services.Greeter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

@Slf4j
@RequiredArgsConstructor
public class GreetingJob implements Job {

    private final Greeter greeter;

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        log.info("******** {}, {} *************", context.getJobDetail().getKey().getName(),
                 greeter.greet((Greeting) jobDataMap.get("greeting")));
    }
}
