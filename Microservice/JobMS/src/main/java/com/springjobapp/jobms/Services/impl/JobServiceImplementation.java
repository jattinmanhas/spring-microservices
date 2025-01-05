package com.springjobapp.jobms.Services.impl;

import com.springjobapp.jobms.Clients.CompanyClient;
import com.springjobapp.jobms.Clients.ReviewClient;
import com.springjobapp.jobms.DTO.JobDTO;
import com.springjobapp.jobms.External.Company;
import com.springjobapp.jobms.External.Review;
import com.springjobapp.jobms.Mapper.JobMapper;
import com.springjobapp.jobms.Models.Job;
import com.springjobapp.jobms.Repository.JobRepository;
import com.springjobapp.jobms.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImplementation implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    private JobRepository jobRepository;

    @Autowired
    private RestTemplate restTemplate;

    private CompanyClient companyClient;

    private ReviewClient reviewClient;

    public JobServiceImplementation(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private JobDTO convertToDTO(Job job) {
        // Company company = restTemplate.getForObject("http://COMPANYMS:8081/company/" + job.getCompanyId(), Company.class);
        Company company = companyClient.getCompany(job.getCompanyId());
        // ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("https://REVIEW:8083/reviews?companyId="+job.getCompanyId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {});
        // List<Review> reviews = reviewResponse.getBody();

        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job, company, reviews);
        jobDTO.setCompany(company);

        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
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
