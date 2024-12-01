package by.clevertec.controllers;

import by.clevertec.services.ReviewServices;
import by.clevertec.services.impl.ReviewServiceImpl;

public class ReviewController {

    public static void main(String[] args) {
        ReviewServices reviewService = new ReviewServiceImpl();
        //add review
//        reviewService.addReview(2L, 4L);

        // update review
//        reviewService.updateReview(1L);

        //delete review
//        reviewService.deleteReview(2L);

        //search reviews by keywords
//        String keyWords = "Great car";
//        reviewService.searchReviewsByKeywords(keyWords);
    }
}
