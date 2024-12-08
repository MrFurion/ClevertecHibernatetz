package by.clevertec.services.impl;

import by.clevertec.dto.CarDtoRequest;
import by.clevertec.dto.CarDtoResponse;
import by.clevertec.exception.CarNotFoundException;
import by.clevertec.exception.CarShowroomNorFoundException;
import by.clevertec.mapper.CarMapper;
import by.clevertec.models.Car;
import by.clevertec.models.CarShowroom;
import by.clevertec.repositories.CarRepository;
import by.clevertec.repositories.CarShowroomRepository;
import by.clevertec.services.CarServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarServices {

    private final CarRepository carRepository;
    private final CarShowroomRepository carShowroomRepository;
    private final CarMapper carMapper;


    @Override
    public Car foundCarById(Long id) {
        var car = carRepository.findById(id);
        return car.orElse(null);
    }


    @Override
    @Transactional
    public void creat(@Validated CarDtoRequest carDtoRequest) {
        Car car = carMapper.toCar(carDtoRequest);
        carRepository.save(car);
    }

    @Override
    @Transactional
    public CarDtoResponse update(CarDtoRequest carDtoRequest, long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            Optional.ofNullable(carDtoRequest.getModel()).ifPresent(car::setModel);
            Optional.ofNullable(carDtoRequest.getYearOfProduction()).ifPresent(car::setYearOfProduction);
            Optional.ofNullable(carDtoRequest.getBrandCar()).ifPresent(car::setBrandCar);
            Optional.ofNullable(carDtoRequest.getPrice()).ifPresent(car::setPrice);
            carRepository.save(car);
            return carMapper.toDto(car);
        } else {
            log.error("Car not found " + id);
            throw new CarNotFoundException("Car not found " + id);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException("Car not found with id: " + id);
        }
        carRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void assignCarToShowroom(Long carId, Long showroomId) {
        carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car not found with id: " + carId));
        CarShowroom showroom = carShowroomRepository.findById(showroomId)
                .orElseThrow(() -> new CarShowroomNorFoundException("Showroom not found with ID: " + showroomId));
        carRepository.assignCarToShowroom(carId, showroom);
    }
}
