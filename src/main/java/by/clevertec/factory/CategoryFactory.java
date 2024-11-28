package by.clevertec.factory;

import by.clevertec.enums.category.CarCategory;
import by.clevertec.models.Category;

public class CategoryFactory {
    public static Category getCategory() {
        return Category.builder()
                .carCategory(CarCategory.COUPE)
                .build();
    }
}
