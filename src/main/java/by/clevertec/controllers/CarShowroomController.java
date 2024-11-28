package by.clevertec.controllers;

import by.clevertec.services.CarShowroomsServices;
import by.clevertec.services.impl.CarShowroomServiceImpl;

public class CarShowroomController {
    public static void main(String[] args) {
        CarShowroomsServices showroom = new CarShowroomServiceImpl();

        //add showroom
//        showroom.addCarShowroom(CarShowroomFactory.getCarShowroom());

        //delete showroom
//        showroom.removeCarShowroom(1l);

        //update showroom
//        showroom.updateCarShowroom(CarShowroomFactory.getCarShowroom(), 2L);
    }
}
