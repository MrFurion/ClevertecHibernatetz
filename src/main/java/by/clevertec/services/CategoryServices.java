package by.clevertec.services;

import by.clevertec.dto.CategoryDtoRequest;
import by.clevertec.dto.CategoryDtoResponse;

public interface CategoryServices {
    void create(CategoryDtoRequest categoryDtoRequest);

    void delete(Long id);

    CategoryDtoResponse update(CategoryDtoRequest categoryDtoRequest, Long id);
}
