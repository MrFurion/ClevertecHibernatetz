package by.clevertec.services;

import by.clevertec.models.Category;

public interface CategoryServices {
    void addCategory(Category category);
    void deleteCategory(Long id);
    void updateCategory(Category category, Long id);
}
