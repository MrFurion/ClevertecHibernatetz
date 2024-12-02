package by.clevertec.controllers;

import by.clevertec.enums.car.CarBrand;
import by.clevertec.enums.category.CarCategory;
import by.clevertec.factory.CarFactory;
import by.clevertec.factory.CarShowroomFactory;
import by.clevertec.models.Car;
import by.clevertec.models.CarShowroom;
import by.clevertec.models.Category;
import by.clevertec.services.CarServices;
import by.clevertec.services.CarShowroomsServices;
import by.clevertec.services.impl.CarServiceImpl;
import by.clevertec.services.impl.CarShowroomServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class CarController {
    public static void main(String[] args) {
        CarServices carService = new CarServiceImpl();
        CarShowroomsServices carShowroomsServices = new CarShowroomServiceImpl();

        // add new car
//        carService.addCar();

        //delete car
//        carService.deleteCarById(1L);

        //update car
//        carService.updateCar(CarFactory.getCar(), 2L);

        //join CarShowroom with Car
//        carService.foundAllCars(1, 5);
//        carShowroomsServices.findAllCarShowrooms();
//        Car car = new Car();
//        car.setId(4L);
//        CarShowroom carShowroom = new CarShowroom();
//        carShowroom.setId(2L);
//        carService.assignCarToShowroom(car, carShowroom);

        //search Brand YearOfProduction Category Price
//        CarBrand carBrand = CarBrand.DODGE;
//        LocalDate year = LocalDate.of(2005,2,2);
//        CarCategory category = CarCategory.COUPE;
//        String price = "10000-12000";
//        carService.foundCarByBrandYearOfProductionCategoryPrice(carBrand, year, category, price);

        //list car search ASC
        carService.findCarsSortedByPriceAsc();
//
        //list car search DESC
//        carService.findCarsSortedByPriceDesc();

        //foundAllCarWithPagination
//        carService.foundAllCars(1, 5);

    }
}
