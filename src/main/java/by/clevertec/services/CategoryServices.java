package by.clevertec.services;

import by.clevertec.models.Category;

import java.util.List;

public interface CategoryServices {
    void addCategory();
    void deleteCategory(Long id);
    void updateCategory(Category category, Long id);
    void linkCategoryWithCars(Long categoryId, List<Long> carIds);
}
