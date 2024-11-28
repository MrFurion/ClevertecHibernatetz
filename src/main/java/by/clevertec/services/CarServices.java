package by.clevertec.services;

import by.clevertec.models.Car;

public interface CarServices {
    void foundCarById(Long id);
    void foundAllCars();
    void addCar(Car car);
    void updateCar(Car car, long id);
    void deleteCarById(Long id);
}
