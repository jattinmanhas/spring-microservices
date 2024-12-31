package com.springjobapp.springjobapp.Job.Repository;

import com.springjobapp.springjobapp.Job.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
