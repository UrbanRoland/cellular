package com.bilingjob.jobs;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.repository.JobRepository;

public class BillingJob implements Job {
    
    private JobRepository jobRepository;
    
    public BillingJob(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    
    @Override
    public String getName() {
        return "BillingJob";
    }
    
    @Override
    public void execute(JobExecution execution) {
        System.out.println("processing billing information");
        execution.setStatus(BatchStatus.COMPLETED);
        execution.setExitStatus(ExitStatus.COMPLETED);
        this.jobRepository.update(execution);
    }

    // Testing the exception handling
/*    @Override
    public void execute(JobExecution execution) {
        try {
            throw new Exception("Unable to process billing information");
        } catch (Exception exception) {
            execution.addFailureException(exception);
            execution.setStatus(BatchStatus.COMPLETED);
            execution.setExitStatus(ExitStatus.FAILED.addExitDescription(exception.getMessage()));
        } finally {
            this.jobRepository.update(execution);
        }
    }*/
}