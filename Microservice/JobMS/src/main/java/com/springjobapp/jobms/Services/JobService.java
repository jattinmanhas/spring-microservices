package com.springjobapp.jobms.Services;


import com.springjobapp.jobms.Models.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean removeJobById(Long id);

    boolean updateJobById(Long id, Job job);
}
