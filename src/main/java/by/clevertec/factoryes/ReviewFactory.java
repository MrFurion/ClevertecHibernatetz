package by.clevertec.factoryes;

import by.clevertec.models.Review;

public class ReviewFactory {
    public static Review createReview() {
        return Review.builder()
                .reviewText("Bad car")
                .rank(1)
                .build();
    }
}
