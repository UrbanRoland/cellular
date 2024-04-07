package com.bilingjob.Schedulers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BillingJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job job;
    
   public BillingJobScheduler(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }
    
    @Scheduled(cron = "0 31 15 * * ?") // Runs the job every day at 15:31
    public void scheduleMyBatchJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        System.out.println("Starting job " + job.getName());
        jobLauncher.run(job, new JobParametersBuilder().addDate("timestamp", new Date())
            .addString("input.file", "src/main/resources/billing-2023-01.csv")
            .addString("output.file", "staging/billing-report-2023-01.csv")
            
            .toJobParameters());
    }
}