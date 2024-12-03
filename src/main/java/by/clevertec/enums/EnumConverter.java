package by.clevertec.enums;

import by.clevertec.interfaces.Describable;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public abstract class EnumConverter<T extends Enum<T> & Describable> implements AttributeConverter<T, String> {

    private final Class<T> enumType;

    protected EnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.getDescription() : null;
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.getDescription().equals(dbData))
                .findFirst()
                .orElse(null);
    }
}