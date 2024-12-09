package by.clevertec.services;

import by.clevertec.models.Review;

public interface ReviewServices {
    void addReview(Long clientId, Long cardId);
    void deleteReview(Long id);
    void updateReview(Long id);
}
