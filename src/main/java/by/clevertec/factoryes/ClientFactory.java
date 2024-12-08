package by.clevertec.factoryes;

import by.clevertec.models.Client;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ClientFactory {
    public static Client createClient() {
        Set<String> cantacts = new HashSet<>();
        cantacts.add("bob@gmail.com");
        cantacts.add("+375293458765");
        cantacts.add("9lHki Kupala 6");
        return Client.builder()
                .name("Bob")
                .contact(cantacts)
                .dateOfRegistration(LocalDate.now())
                .build();
    }
}
