package by.clevertec.enums.category;

import by.clevertec.interfaces.Describable;
import lombok.Getter;

@Getter
public enum CarCategory implements Describable {
    SEDAN("Седан"),
    HATCHBACK("Хэтчбек"),
    CROSSOVER("Кроссовер"),
    COUPE("Купе"),
    CONVERTIBLE("Кабриолет"),
    WAGON("Универсал"),
    MINIVAN("Минивэн"),
    PICKUP("Пикап"),
    SPORTSCAR("Спортивный автомобиль"),
    ELECTRIC("Электромобиль"),
    OFFROAD("Внедорожник"),
    MICROCAR("Микроавтомобиль"),
    TRUCK("Грузовик"),
    VAN("Фургон"),
    BUS("Автобус");

    private final String description;

    CarCategory(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
