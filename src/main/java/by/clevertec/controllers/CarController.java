package by.clevertec.controllers;

import by.clevertec.dto.CarDtoRequest;
import by.clevertec.dto.CarDtoResponse;
import by.clevertec.exception.CarNotFoundException;
import by.clevertec.models.Car;
import by.clevertec.services.CarServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/app/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarServices carService;


    @GetMapping("/{id}")
    public ResponseEntity<Car> show(@PathVariable Long id) {
        Car car = carService.foundCarById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<String> creat(@Validated @RequestBody CarDtoRequest carDtoRequest) {
        System.out.println("1----");
        System.out.println(carDtoRequest.toString());
        carService.creat(carDtoRequest);
        return ResponseEntity.created(URI.create("/app/cars")).body("Car successfully created : " + carDtoRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            carService.delete(id);
            return ResponseEntity.ok("Car successfully deleted with id: " + id);
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found with id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @Validated @RequestBody CarDtoRequest carDtoRequest) {

        CarDtoResponse carDtoResponse = carService.update(carDtoRequest, id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Car successfully updated.");
        response.put("updatedCar", carDtoResponse);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{carId}/showroom/{showroomId}")
    public ResponseEntity<String> assignCarToShowroom(
            @PathVariable Long carId,
            @PathVariable Long showroomId) {
        carService.assignCarToShowroom(carId, showroomId);
        return ResponseEntity.ok("Car assigned to showroom successfully!");
    }
}
