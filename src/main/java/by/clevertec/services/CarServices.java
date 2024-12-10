package by.clevertec.services;

import by.clevertec.dto.CarDtoRequest;
import by.clevertec.dto.CarDtoResponse;
import by.clevertec.models.Car;


public interface CarServices {
    Car foundCarById(Long id);

    void creat(CarDtoRequest carDtoResponse);

    CarDtoResponse update(CarDtoRequest carDtoResponse, long id);

    void delete(Long id);

    void assignCarToShowroom(Long carId, Long showroomId);
}
