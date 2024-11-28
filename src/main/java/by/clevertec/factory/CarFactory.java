package by.clevertec.factory;

import by.clevertec.enums.car.CarBrand;
import by.clevertec.models.Car;

import java.time.LocalDate;

public class CarFactory {
    public static Car getCar(){
        return Car.builder()
                .model("C-5")
                .brandCar(CarBrand.CHEVROLET)
                .yearOfProduction(LocalDate.of(2004, 12, 5))
                .price("12000")
                .build();
    }
}
