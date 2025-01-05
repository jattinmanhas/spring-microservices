package com.springjobapp.jobms.Services;


import com.springjobapp.jobms.DTO.JobDTO;
import com.springjobapp.jobms.Models.Job;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getJobById(Long id);

    boolean removeJobById(Long id);

    boolean updateJobById(Long id, Job job);
}
