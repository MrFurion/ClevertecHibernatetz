package by.clevertec.controllers;

import by.clevertec.dto.CarShowroomDtoRequest;
import by.clevertec.dto.CarShowroomDtoResponse;
import by.clevertec.exception.CarNotFoundException;
import by.clevertec.exception.CarShowroomNotFoundException;
import by.clevertec.services.CarShowroomsServices;
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
@RequestMapping("/app/showrooms")
@RequiredArgsConstructor
public class CarShowroomController {

    private final CarShowroomsServices carShowroomsServices;

    @PostMapping
    public ResponseEntity<String> creat(@Validated @RequestBody CarShowroomDtoRequest carShowroomDtoRequest) {

        carShowroomsServices.create(carShowroomDtoRequest);
        return ResponseEntity.created(URI.create("/app/showrooms")).body("CarShowroom successfully created : " + carShowroomDtoRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            carShowroomsServices.delete(id);
            return ResponseEntity.ok("CarShowroom successfully deleted with id: " + id);
        } catch (CarShowroomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CarShowroom not found with id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @Validated @RequestBody CarShowroomDtoRequest carShowroomDtoRequest) {

        CarShowroomDtoResponse carShowroomDtoResponse = carShowroomsServices.update(carShowroomDtoRequest, id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Car showroom successfully updated.");
        response.put("updatedShowroom", carShowroomDtoResponse);

        return ResponseEntity.ok(response);
    }
}
