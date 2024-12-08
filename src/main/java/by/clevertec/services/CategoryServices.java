package by.clevertec.services;

import by.clevertec.dto.CategoryDtoRequest;
import by.clevertec.models.Category;

public interface CategoryServices {
    void create(CategoryDtoRequest categoryDtoRequest);

    void delete(Long id);

    void update(Category category, Long id);
}
