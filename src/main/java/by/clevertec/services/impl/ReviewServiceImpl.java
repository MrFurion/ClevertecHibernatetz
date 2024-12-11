package by.clevertec.services.impl;

import by.clevertec.dto.ReviewDtoRequest;
import by.clevertec.dto.ReviewDtoResponse;
import by.clevertec.exception.CarNotFoundException;
import by.clevertec.exception.ClientNotFoundException;
import by.clevertec.exception.ReviewNotFoundException;
import by.clevertec.mapper.ReviewMapper;
import by.clevertec.models.Car;
import by.clevertec.models.Client;
import by.clevertec.models.Review;
import by.clevertec.repositories.CarRepository;
import by.clevertec.repositories.ClientRepository;
import by.clevertec.repositories.ReviewRepository;
import by.clevertec.services.ReviewServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewServices {

    private final ReviewRepository reviewRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ReviewMapper reviewMapper;

    @Override
    @Transactional
    public void creat(Long clientId, Long carId, ReviewDtoRequest reviewDtoRequest) {

        System.out.println(reviewDtoRequest.getReviewText() + "<--------1");
        Review review = reviewMapper.toReview(reviewDtoRequest);
        System.out.println(review.getReviewText() + "<------2");
        if (review != null) {
            Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new ClientNotFoundException("Client not found with id " + clientId));
            Car car = carRepository.findById(carId)
                    .orElseThrow(() -> new CarNotFoundException("Car not found with id " + carId));
            review.setClients(client);
            review.setCars(car);
            reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Client object cannot be null");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException("Review not found with id " + id);
        }
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ReviewDtoResponse update(ReviewDtoRequest reviewDtoRequest, Long id) {

        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            Optional.ofNullable(reviewDtoRequest.getReviewText()).ifPresent(review::setReviewText);
            Optional.of(reviewDtoRequest.getRank()).ifPresent(review::setRank);
            reviewRepository.save(review);
            return reviewMapper.toReviewDto(review);
        } else {
            log.error("Review not found with id " + id);
            throw new ReviewNotFoundException("Review not found with id " + id);
        }
    }
}
