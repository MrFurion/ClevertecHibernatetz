package by.clevertec.util;

import by.clevertec.enums.category.CarCategory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.util.Arrays;

public class CarCategoryDeserializer extends JsonDeserializer<CarCategory> {

    @Override
    public CarCategory deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText().trim().toUpperCase();
        try {
            return CarCategory.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidFormatException(
                    p,
                    "Invalid value for CarCategory. Must be one of: " + Arrays.toString(CarCategory.values()),
                    value,
                    CarCategory.class
            );
        }
    }
}
