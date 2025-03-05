package com.nothing.Reviews.Review.impl;


import com.nothing.Reviews.Review.Review;
import com.nothing.Reviews.Review.ReviewController;
import com.nothing.Reviews.Review.ReviewRepository;
import com.nothing.Reviews.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {



    private ReviewRepository reviewRepository;
//    private CompanyService companyService;


    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }



    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId!=null && review!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return  reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview( Long reviewId, Review updatedreview) {
        Review review =reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setTitle(updatedreview.getTitle());
            review.setDescription(updatedreview.getDescription());
            review.setRating(updatedreview.getRating());
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview( Long reviewId) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){

            reviewRepository.delete(review);

            return true;
        }else{
            return false;
        }
    }
}
