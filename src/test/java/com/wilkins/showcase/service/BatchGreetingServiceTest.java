package com.wilkins.showcase.service;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
//@SpringBootTest
class BatchGreetingServiceTest {
    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;
    TestJob job = new TestJob();

    @Test
    void canLaunchJob() throws Exception {
        var jobLauncher = jobLauncherTestUtils.getJobLauncher();
        var jobExecution = jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
        assertThat(jobExecution.isRunning()).isFalse();
        assertThat(job.hasRun).isTrue();
    }

    static class TestJob implements Job {

        boolean hasRun = false;

        @Override
        public String getName() {
            return "test-job";
        }

        @Override
        public void execute(JobExecution execution) {
            hasRun = true;
        }
    }
}