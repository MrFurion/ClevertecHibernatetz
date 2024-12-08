package by.clevertec.services;

import by.clevertec.models.Category;

import java.util.List;

public interface CategoryServices {
    void create();
    void delete(Long id);
    void update(Category category, Long id);
}
