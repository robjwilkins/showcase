package com.wilkins.showcase.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

@Service
public class BatchGreetingService implements GreetingService {
    private final JobLauncher jobLauncher;
    private final Job greetingJob;

    public BatchGreetingService(JobLauncher jobLauncher, Job greetingJob) {
        this.jobLauncher = jobLauncher;
        this.greetingJob = greetingJob;
    }

    @Override
    public String greet(String name, String salutation) {
        try {
            var execution = jobLauncher.run(greetingJob, new JobParametersBuilder()
                    .addJobParameter("salutation", salutation, String.class)
                    .addJobParameter("name", name, String.class)
                    .toJobParameters());
            return execution.getExecutionContext().getString("greeting");
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}
