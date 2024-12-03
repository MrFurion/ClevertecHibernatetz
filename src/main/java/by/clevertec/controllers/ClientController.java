package by.clevertec.controllers;

import by.clevertec.services.ClientServices;
import by.clevertec.services.impl.ClientServiceImpl;

public class ClientController {
    public static void main(String[] args) {
        ClientServices client = new ClientServiceImpl();
        //add client
//        client.createClient();

        //client update
//        client.updateClient(ClientFactory.createClient(), 3L);

        //delete client

//        client.deleteClient(1l);
        //buyCar
        client.buyCar(2L, 3L);
    }

}
