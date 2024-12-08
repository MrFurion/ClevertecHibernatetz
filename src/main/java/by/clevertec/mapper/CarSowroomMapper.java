package by.clevertec.mapper;

import by.clevertec.dto.CarShowroomDtoRequest;
import by.clevertec.dto.CarShowroomDtoResponse;
import by.clevertec.enums.car.CarBrandConverter;
import by.clevertec.models.CarShowroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = CarBrandConverter.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarSowroomMapper {
    CarSowroomMapper INSTANCE = Mappers.getMapper(CarSowroomMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    CarShowroom toCarShowroom(CarShowroomDtoRequest carShowroomDtoRequest);

    CarShowroomDtoResponse toCarDtoResponse(CarShowroom carShowroom);
}
