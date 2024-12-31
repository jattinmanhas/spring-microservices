package com.springjobapp.springjobapp.Job.Services.impl;

import com.springjobapp.springjobapp.Job.Models.Job;
import com.springjobapp.springjobapp.Job.Repository.JobRepository;
import com.springjobapp.springjobapp.Job.Services.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImplementation implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    private JobRepository jobRepository;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }
    @Override
    public Job getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return job;
        /* return jobs.stream()
                .filter(job -> job.getId() == id)
                .findFirst()
                .orElse(null); // Returns null if no job is found */
    }

    @Override
    public boolean removeJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
        /*Job jobToRemove = jobs.stream()
                .filter(job -> job.getId() == id)
                .findFirst()
                .orElse(null);

        if (jobToRemove != null) {
            jobs.remove(jobToRemove);
        }

        return jobToRemove;*/
    }

    @Override
    public boolean updateJobById(Long id, Job job) {
        Optional<Job> jobToUpdate = jobRepository.findById(id);
        if(jobToUpdate.isPresent()){
            jobToUpdate.get().setTitle(job.getTitle());
            jobToUpdate.get().setDescription(job.getDescription());
            jobToUpdate.get().setMaxSalary(job.getMaxSalary());
            jobToUpdate.get().setMinSalary(job.getMinSalary());
            jobToUpdate.get().setLocation(job.getLocation());

            jobRepository.save(jobToUpdate.get());
            return true;
        }
/*        Job jobToUpdate = jobs.stream()
                .filter(existingJob -> existingJob.getId() == id)
                .findFirst()
                .orElse(null);
    
        if (jobToUpdate != null) {
            jobToUpdate.setTitle(job.getTitle());
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobToUpdate.setMinSalary(job.getMinSalary());
            jobToUpdate.setLocation(job.getLocation());
        }
    
        return jobToUpdate;*/
        return false;
    }
}
