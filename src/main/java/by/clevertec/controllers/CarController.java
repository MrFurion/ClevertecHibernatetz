package by.clevertec.controllers;

import by.clevertec.services.CarServices;
import by.clevertec.services.impl.CarServiceImpl;

public class CarController {
    public static void main(String[] args) {
        CarServices carService = new CarServiceImpl();

        // add new car
//        carService.addCar(CarFactory.getCar());

        //delete car
//        carService.deleteCarById(1L);

        //update car
//        carService.updateCar(CarFactory.getCar(), 2L);
    }
}
