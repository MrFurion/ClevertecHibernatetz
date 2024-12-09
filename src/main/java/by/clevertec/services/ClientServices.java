package by.clevertec.services;

import by.clevertec.dto.ClientDtoRequest;
import by.clevertec.dto.ClientDtoResponse;

public interface ClientServices {
    void create(ClientDtoRequest request);

    ClientDtoResponse update(ClientDtoRequest clientDtoRequest, Long id);

    void delete(Long id);
}
