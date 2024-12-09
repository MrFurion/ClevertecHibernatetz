package by.clevertec.services;

import by.clevertec.models.Client;

public interface ClientServices {
    void createClient();
    void updateClient(Client clientUpdate, Long id);
    void deleteClient(Long id);

}
