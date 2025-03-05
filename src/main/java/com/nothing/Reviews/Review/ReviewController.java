package com.nothing.Reviews.Review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReview(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,@RequestBody Review review){
        boolean added=reviewService.addReview(companyId,review);
        if(added){
            return new ResponseEntity<>("Review Added Successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Is Not Added Successfully",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(reviewId),HttpStatus.OK);
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Review review){
        boolean updated=reviewService.updateReview(reviewId,review);
        if(updated){
            return new ResponseEntity<>("Review update Successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Is Not update Successfully",HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean deleted=reviewService.deleteReview(reviewId);
        if(deleted){
            return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Is Not Deleted Successfully",HttpStatus.NOT_FOUND);
        }

    }
}
