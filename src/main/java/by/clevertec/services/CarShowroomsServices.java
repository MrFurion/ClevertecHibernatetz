package by.clevertec.services;

import by.clevertec.models.CarShowroom;

public interface CarShowroomsServices {
    void addCarShowroom();
    void removeCarShowroom(Long id);
    void updateCarShowroom(CarShowroom carShowroom, Long id);
    void findAllCarShowrooms();
}
