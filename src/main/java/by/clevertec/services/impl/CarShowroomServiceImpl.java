package by.clevertec.services.impl;

import by.clevertec.dto.CarShowroomDtoRequest;
import by.clevertec.dto.CarShowroomDtoResponse;
import by.clevertec.exception.CarNotFoundException;
import by.clevertec.exception.CarShowroomNotFoundException;
import by.clevertec.mapper.CarSowroomMapper;
import by.clevertec.models.CarShowroom;
import by.clevertec.repositories.CarShowroomRepository;
import by.clevertec.services.CarShowroomsServices;
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
public class CarShowroomServiceImpl implements CarShowroomsServices {
    private final CarShowroomRepository carShowroomRepository;
    private final CarSowroomMapper carSowroomMapper;

    @Transactional
    @Override
    public void create(@Validated CarShowroomDtoRequest carShowroomDtoRequest) {
        CarShowroom carShowroom = carSowroomMapper.toCarShowroom(carShowroomDtoRequest);
        carShowroomRepository.save(carShowroom);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!carShowroomRepository.existsById(id)) {
            throw new CarNotFoundException("CarShowroom not found with id: " + id);
        }
        carShowroomRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CarShowroomDtoResponse update(CarShowroomDtoRequest carShowroomDtoRequest, Long id) {
        Optional<CarShowroom> optionalCarShowroom = carShowroomRepository.findById(id);
        if (optionalCarShowroom.isPresent()) {
            CarShowroom carShowroom = optionalCarShowroom.get();
            Optional.ofNullable(carShowroomDtoRequest.getName()).ifPresent(carShowroom::setName);
            Optional.ofNullable(carShowroomDtoRequest.getAddress()).ifPresent(carShowroom::setAddress);
            carShowroomRepository.save(carShowroom);
            return carSowroomMapper.toCarDtoResponse(carShowroom);
        } else {
            log.error("CarShowroom not found with id: " + id);
            throw new CarShowroomNotFoundException("CarShowroom not found with id: " + id);
        }
    }
}
