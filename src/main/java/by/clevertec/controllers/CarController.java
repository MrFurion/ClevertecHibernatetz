package by.clevertec.controllers;

import by.clevertec.models.Car;
import by.clevertec.services.CarServices;
import by.clevertec.services.CarShowroomsServices;
import by.clevertec.services.impl.CarServiceImpl;
import by.clevertec.services.impl.CarShowroomServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/cars")
public class CarController {
    private CarServices carService;

    @GetMapping
    public ResponseEntity<List<Car>> show(){
        List<Car> car = carService.foundCarAll();
        return ResponseEntity.ok(car);
    }

//    public static void main(String[] args) {
//        CarServices carService = new CarServiceImpl();
//        CarShowroomsServices carShowroomsServices = new CarShowroomServiceImpl();

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
//        carService.findCarsSortedByPriceAsc();
//
        //list car search DESC
//        carService.findCarsSortedByPriceDesc();

        //foundAllCarWithPagination
//        carService.foundAllCars(1, 5);


//    }
}
