package by.clevertec.services;

import by.clevertec.models.CarShowroom;

public interface CarShowroomsServices {
    void addCarShowroom(CarShowroom carShowroom);
    void removeCarShowroom(Long id);
    void updateCarShowroom(CarShowroom carShowroom, Long id);
}
