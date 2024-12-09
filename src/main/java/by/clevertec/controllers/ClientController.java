package by.clevertec.controllers;

import by.clevertec.dto.ClientDtoRequest;
import by.clevertec.dto.ClientDtoResponse;
import by.clevertec.exception.ClientNotFoundException;
import by.clevertec.services.ClientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServices clientServices;

    @PostMapping
    public ResponseEntity<String> creat(@Validated @RequestBody ClientDtoRequest clientDtoRequest) {
        clientServices.create(clientDtoRequest);
        return ResponseEntity.created(URI.create("/app/clients")).body("Client successfully created : " + clientDtoRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            clientServices.delete(id);
            return ResponseEntity.ok("Client successfully deleted with id: " + id);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found with id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @Validated @RequestBody ClientDtoRequest clientDtoRequest) {

        ClientDtoResponse clientDtoResponse = clientServices.update(clientDtoRequest, id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Client successfully updated.");
        response.put("updatedCategory", clientDtoResponse);

        return ResponseEntity.ok(response);
    }
}
