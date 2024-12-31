package com.springjobapp.springjobapp.Job.Repository;

import com.springjobapp.springjobapp.Job.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByCompanyId(long companyId);
}
