package com.springjobapp.jobms.Services;


import com.springjobapp.jobms.DTO.JobWithCompanyDTO;
import com.springjobapp.jobms.Models.Job;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);

    JobWithCompanyDTO getJobById(Long id);

    boolean removeJobById(Long id);

    boolean updateJobById(Long id, Job job);
}
