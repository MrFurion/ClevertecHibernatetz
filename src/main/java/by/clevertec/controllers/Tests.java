package by.clevertec.controllers;

import by.clevertec.config.SpringConfig;
import by.clevertec.models.Car;
import by.clevertec.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class Tests {
    private final CarRepository carRepository;
    public  void carCar(){
        Car car = carRepository.findCarById(2L);
        System.out.println(car.getModel());
    }
    public static void main(String[] args) {

    }
}
class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Tests tests = context.getBean(Tests.class);
        tests.carCar();
        context.close();
    }
}