package by.clevertec.factory;

import by.clevertec.enums.car.CarBrand;
import by.clevertec.models.Car;

import java.time.LocalDate;

public class CarFactory {
    public static Car getCar() {
        return Car.builder()
                .model("C-6")
                .brandCar(CarBrand.DODGE)
                .yearOfProduction(LocalDate.of(2005, 2, 2))
                .price("10000")
                .build();
    }
}
