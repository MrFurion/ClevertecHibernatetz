package by.clevertec.factory;


import by.clevertec.models.CarShowroom;

public class CarShowroomFactory {
    public static CarShowroom getCarShowroom() {
        return CarShowroom.builder()
                .name("Auto Digest")
                .address("Selickogo 10")
                .build();
    }
}
