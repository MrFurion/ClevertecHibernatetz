package by.clevertec.mapper;

import by.clevertec.dto.CarDtoRequest;
import by.clevertec.dto.CarDtoResponse;
import by.clevertec.enums.car.CarBrandConverter;
import by.clevertec.models.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = CarBrandConverter.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "showroom", ignore = true)
    @Mapping(target = "review", ignore = true)
    @Mapping(target = "clientas", ignore = true)
    @Mapping(target = "categoryes", ignore = true)
    @Mapping(target = "yearOfProduction", source = "yearOfProduction", dateFormat = "yyyy-MM-dd")
    Car toCar(CarDtoRequest carDtoRequest);

    @Mapping(target = "model", source = "model")
    @Mapping(target = "brandCar", source = "brandCar")
    @Mapping(target = "yearOfProduction", source = "yearOfProduction")
    @Mapping(target = "price", source = "price")
    CarDtoResponse toDto(Car car);
}
