package com.springjobapp.jobms.Services.impl;

import com.springjobapp.jobms.DTO.JobWithCompanyDTO;
import com.springjobapp.jobms.External.Company;
import com.springjobapp.jobms.Mapper.JobMapper;
import com.springjobapp.jobms.Models.Job;
import com.springjobapp.jobms.Repository.JobRepository;
import com.springjobapp.jobms.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImplementation implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    private JobRepository jobRepository;

    @Autowired
    private RestTemplate restTemplate;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private JobWithCompanyDTO convertToDTO(Job job) {
        Company company = restTemplate.getForObject("http://COMPANYMS:8081/company/" + job.getCompanyId(), Company.class);
        JobWithCompanyDTO jobWithCompanyDTO = JobMapper.mapToJobWithCompanyDto(job, company);
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);

        return convertToDTO(job);
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
