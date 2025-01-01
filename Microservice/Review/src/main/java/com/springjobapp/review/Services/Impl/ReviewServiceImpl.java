package com.springjobapp.review.Services.Impl;

import com.springjobapp.review.Models.Review;
import com.springjobapp.review.Repository.ReviewRepository;
import com.springjobapp.review.Services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(long companyId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);

        return reviews;
    }

    @Override
    public boolean createReview(long companyId, Review review) {
        if(companyId > 0 && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateReviewById(long reviewId, Review review) {
        Review reviewToUpdate = reviewRepository.findById(reviewId).orElse(null);
        if(reviewToUpdate != null){
            review.setTitle(review.getTitle());
            review.setDescription(review.getDescription());
            review.setRating(review.getRating());
            review.setCompanyId(reviewToUpdate.getCompanyId());

            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeReviewById(long reviewId) {
        Review reviewToRemove = reviewRepository.findById(reviewId).orElse(null);
        if(reviewToRemove != null){
            reviewRepository.delete(reviewToRemove);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }
}
