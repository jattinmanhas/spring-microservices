package com.springjobapp.springjobapp.Job.Controllers;

import com.springjobapp.springjobapp.Job.Models.Review;
import com.springjobapp.springjobapp.Job.Services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody Review review, @PathVariable long companyId){
        boolean isReviewSaved = reviewService.createReview(companyId, review);
        if(isReviewSaved){
            return new ResponseEntity<>("Review saved successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review not saved", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable long reviewId, @PathVariable long companyId){
        Review review = reviewService.getReviewById(companyId, reviewId);
        if(review == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable long reviewId, @RequestBody Review review, @PathVariable long companyId){
        boolean isReviewUpdated = reviewService.updateReviewById(companyId, reviewId, review);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not updated", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> removeReview(@PathVariable long reviewId, @PathVariable long companyId){
        boolean isReviewRemoved = reviewService.removeReviewById(companyId, reviewId);
        if(isReviewRemoved){
            return new ResponseEntity<>("Review removed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not removed", HttpStatus.BAD_REQUEST);
    }
}
