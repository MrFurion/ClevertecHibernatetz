package by.clevertec.mapper;

import by.clevertec.dto.ClientDtoRequest;
import by.clevertec.dto.ClientDtoResponse;
import by.clevertec.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateOfRegistration", ignore = true)
    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Client toClient(ClientDtoRequest clientDtoRequest);

    ClientDtoResponse toClientDtoResponse(Client client);
}
