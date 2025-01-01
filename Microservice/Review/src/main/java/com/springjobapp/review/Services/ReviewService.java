package com.springjobapp.review.Services;


import com.springjobapp.review.Models.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(long companyId);
    boolean createReview(long companyId, Review review);
    Review getReviewById(long reviewId);
    boolean updateReviewById(long reviewId, Review review);
    boolean removeReviewById(long reviewId);
}
