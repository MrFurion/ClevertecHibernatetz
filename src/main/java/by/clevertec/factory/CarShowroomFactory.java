package by.clevertec.factory;


import by.clevertec.models.CarShowroom;

public class CarShowroomFactory {
    public static CarShowroom getCarShowroom() {
        return CarShowroom.builder()
                .name("Big auto")
                .address("Korganovo 18")
                .build();
    }
}
