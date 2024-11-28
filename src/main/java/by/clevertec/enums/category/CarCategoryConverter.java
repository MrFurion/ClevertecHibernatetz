package by.clevertec.enums.category;

import by.clevertec.enums.EnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CarCategoryConverter extends EnumConverter<CarCategory> {
    public CarCategoryConverter() {
        super(CarCategory.class);
    }
}