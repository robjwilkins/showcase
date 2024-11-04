package com.wilkins.showcase.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GreetingJob implements Job {
    @Override
    public String getName() {
        return "Greeting Job";
    }

    @Override
    public void execute(JobExecution execution) {
        var name = execution.getJobParameters().getString("name");
        var salutation = execution.getJobParameters().getString("salutation");
        log.info("{} {}", salutation, name);
    }
}
