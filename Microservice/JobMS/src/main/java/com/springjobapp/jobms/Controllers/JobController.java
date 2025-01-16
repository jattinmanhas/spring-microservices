package com.springjobapp.jobms.Controllers;

import com.springjobapp.jobms.DTO.JobDTO;
import com.springjobapp.jobms.Models.Job;
import com.springjobapp.jobms.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAllJobs(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        System.out.println(job.toString());
        jobService.createJob(job);
        return new ResponseEntity<>("Job saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO job = jobService.getJobById(id);
        if(job == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean removed = jobService.removeJobById(id);

        if(!removed){
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job){
        boolean updatedJob = jobService.updateJobById(id, job);

        if(!updatedJob){
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
    }
}
