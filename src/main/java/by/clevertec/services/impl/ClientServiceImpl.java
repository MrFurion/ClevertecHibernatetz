package by.clevertec.services.impl;

import by.clevertec.dto.ClientDtoRequest;
import by.clevertec.dto.ClientDtoResponse;
import by.clevertec.exception.CarNotFoundException;
import by.clevertec.exception.ClientNotFoundException;
import by.clevertec.mapper.ClientMapper;
import by.clevertec.models.Car;
import by.clevertec.models.Client;
import by.clevertec.repositories.CarRepository;
import by.clevertec.repositories.ClientRepository;
import by.clevertec.services.ClientServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientServices {

    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public void create(ClientDtoRequest clientDtoRequest) {
        Client client = clientMapper.toClient(clientDtoRequest);
        client.setDateOfRegistration(LocalDate.now());
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public ClientDtoResponse update(ClientDtoRequest clientDtoRequest, Long id) {

        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            Optional.ofNullable(clientDtoRequest.getName()).ifPresent(client::setName);
            Optional.ofNullable(clientDtoRequest.getContact()).ifPresent(client::setContact);

            clientRepository.save(client);
            return clientMapper.toClientDtoResponse(client);
        } else {
            log.error("Client not found with id " + id);
            throw new ClientNotFoundException("Client not found with id " + id);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void bayCar(Long clientId, Long carId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + clientId));
        log.error("Client not found with id: " + clientId);

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id " + carId));
        log.error("Car not found with id " + carId);

        client.getCars().add(car);

        clientRepository.save(client);
    }
}
