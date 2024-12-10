package by.clevertec.services;

import by.clevertec.dto.ReviewDtoRequest;
import by.clevertec.dto.ReviewDtoResponse;

public interface ReviewServices {
    void creat(Long clientId, Long cardId, ReviewDtoRequest reviewDtoRequest);
    void delete(Long id);
    ReviewDtoResponse update(ReviewDtoRequest reviewDtoRequest, Long id);
}
