package by.clevertec.controllers;

import by.clevertec.dto.ClientDtoRequest;
import by.clevertec.dto.ClientDtoResponse;
import by.clevertec.dto.ReviewDtoRequest;
import by.clevertec.dto.ReviewDtoResponse;
import by.clevertec.exception.ClientNotFoundException;
import by.clevertec.exception.ReviewNotFoundException;
import by.clevertec.services.ReviewServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewServices reviewServices;

    @PostMapping("/{clientId}/car/{carId}")
    public ResponseEntity<String> create(@PathVariable Long clientId,
                                         @PathVariable Long carId,
                                         @Validated @RequestBody ReviewDtoRequest reviewDtoRequest) {
        reviewServices.creat(clientId, carId, reviewDtoRequest);

        return ResponseEntity.created(URI.create("/app/review")).body("Review successfully created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            reviewServices.delete(id);
            return ResponseEntity.ok("Review successfully deleted with id: " + id);
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found with id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @Validated @RequestBody ReviewDtoRequest reviewDtoRequest) {

        ReviewDtoResponse reviewDtoResponse = reviewServices.update(reviewDtoRequest, id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Review successfully updated.");
        response.put("updatedReview", reviewDtoResponse);

        return ResponseEntity.ok(response);
    }
}
