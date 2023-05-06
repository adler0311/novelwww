package com.example.webnovel.batch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AggregateJobScheduler {

    private final List<Job> jobs;
    private final JobLauncher jobLauncher;

    public AggregateJobScheduler(List<Job> jobs, JobLauncher jobLauncher) {
        this.jobs = jobs;
        this.jobLauncher = jobLauncher;
    }


    @Scheduled(fixedDelay = 5 * 1000L)
    public void executeJob() {
        try {
            for (Job job : jobs) {
                jobLauncher.run(
                        job,
                        new JobParametersBuilder()
                                .addString("datetime", LocalDateTime.now().toString())
                                .toJobParameters()
                );
            }
        } catch (JobInstanceAlreadyCompleteException | JobRestartException | JobParametersInvalidException |
                 JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        }
    }

}
