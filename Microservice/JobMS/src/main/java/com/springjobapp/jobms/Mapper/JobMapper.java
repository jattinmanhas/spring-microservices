package com.springjobapp.jobms.Mapper;

import com.springjobapp.jobms.DTO.JobDTO;
import com.springjobapp.jobms.External.Company;
import com.springjobapp.jobms.External.Review;
import com.springjobapp.jobms.Models.Job;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(Job job, Company company, List<Review> review) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(review);

        return jobDTO;
    }
}
