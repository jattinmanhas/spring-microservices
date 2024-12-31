package com.springjobapp.springjobapp.Job.Services;

import com.springjobapp.springjobapp.Job.Models.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(long companyId);
    boolean createReview(long companyId, Review review);
    Review getReviewById(long companyId, long reviewId);
    boolean updateReviewById(long companyId, long reviewId, Review review);
    boolean removeReviewById(long companyId, long reviewId);
}
