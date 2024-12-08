package by.clevertec.services;

import by.clevertec.dto.CarShowroomDtoRequest;
import by.clevertec.dto.CarShowroomDtoResponse;

public interface CarShowroomsServices {
    void create(CarShowroomDtoRequest request);

    void delete(Long id);

    CarShowroomDtoResponse update(CarShowroomDtoRequest carShowroomDtoRequest, Long id);
}
