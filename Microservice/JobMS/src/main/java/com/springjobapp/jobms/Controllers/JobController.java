package com.springjobapp.jobms.Controllers;

import com.springjobapp.jobms.DTO.JobWithCompanyDTO;
import com.springjobapp.jobms.Models.Job;
import com.springjobapp.jobms.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        System.out.println(job.toString());
        jobService.createJob(job);
        return new ResponseEntity<>("Job saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobWithCompanyDTO> getJobById(@PathVariable Long id){
        JobWithCompanyDTO job = jobService.getJobById(id);
        if(job == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean removed = jobService.removeJobById(id);

        if(!removed){
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/job/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job){
        boolean updatedJob = jobService.updateJobById(id, job);

        if(!updatedJob){
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
    }
}
