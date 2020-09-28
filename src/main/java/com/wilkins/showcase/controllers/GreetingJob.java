package com.wilkins.showcase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class GreetingJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        log.info("{}, {}, {}", context.getJobDetail().getKey().getName(),
                jobDataMap.getString("salutation"),
                jobDataMap.getString("name"));
    }
}
