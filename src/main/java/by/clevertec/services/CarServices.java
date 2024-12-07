package by.clevertec.services;

import by.clevertec.enums.car.CarBrand;
import by.clevertec.enums.category.CarCategory;
import by.clevertec.models.Car;
import by.clevertec.models.CarShowroom;

import java.time.LocalDate;
import java.util.List;

public interface CarServices {
    void foundCarById(Long id);

    void foundCarByBrandYearOfProductionCategoryPrice(CarBrand carBrand,
                                                      LocalDate yearOfProduction,
                                                      CarCategory category,
                                                      String price);

    void findCarsSortedByPriceAsc();

    void findCarsSortedByPriceDesc();

    void foundAllCars(int pageNumber, int pageSize);

    void addCar();

    void updateCar(Car car, long id);

    void deleteCarById(Long id);

    void assignCarToShowroom(Car car, CarShowroom showroom);
    List<Car> foundCarAll();
}
