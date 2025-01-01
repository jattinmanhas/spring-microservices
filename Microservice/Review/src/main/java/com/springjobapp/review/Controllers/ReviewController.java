package com.springjobapp.review.Controllers;


import com.springjobapp.review.Models.Review;
import com.springjobapp.review.Services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody Review review, @RequestParam long companyId){
        boolean isReviewSaved = reviewService.createReview(companyId, review);
        if(isReviewSaved){
            return new ResponseEntity<>("Review saved successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review not saved", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable long reviewId){
        Review review = reviewService.getReviewById(reviewId);
        if(review == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable long reviewId, @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReviewById(reviewId, review);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not updated", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> removeReview(@PathVariable long reviewId){
        boolean isReviewRemoved = reviewService.removeReviewById(reviewId);
        if(isReviewRemoved){
            return new ResponseEntity<>("Review removed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not removed", HttpStatus.BAD_REQUEST);
    }
}
