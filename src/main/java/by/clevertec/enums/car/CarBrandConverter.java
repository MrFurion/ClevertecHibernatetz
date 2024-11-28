package by.clevertec.enums.car;

import by.clevertec.enums.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CarBrandConverter extends EnumConverter<CarBrand> {
    public CarBrandConverter() {
        super(CarBrand.class);
    }
}
