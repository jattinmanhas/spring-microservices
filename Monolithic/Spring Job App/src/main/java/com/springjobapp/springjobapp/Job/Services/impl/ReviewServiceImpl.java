package com.springjobapp.springjobapp.Job.Services.impl;

import com.springjobapp.springjobapp.Job.Models.Company;
import com.springjobapp.springjobapp.Job.Models.Review;
import com.springjobapp.springjobapp.Job.Repository.ReviewRepository;
import com.springjobapp.springjobapp.Job.Services.CompanyService;
import com.springjobapp.springjobapp.Job.Services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(long companyId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);

        return reviews;
    }

    @Override
    public boolean createReview(long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateReviewById(long companyId, long reviewId, Review review) {
        if(companyService.getCompanyById(companyId) != null){
            review.setCompany(companyService.getCompanyById(companyId));
            review.setId(reviewId);

            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeReviewById(long companyId, long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)){
            Review reviewToRemove = reviewRepository.findById(reviewId).orElse(null);
            Company company = reviewToRemove.getCompany();
            company.getReviews().remove(reviewToRemove);
            reviewToRemove.setCompany(null);
            companyService.UpdateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(long companyId, long reviewId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);

        return reviews.stream().filter(review -> review.getId() == reviewId).findFirst().orElse(null);
    }
}
