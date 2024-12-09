package by.clevertec.services.impl;

import by.clevertec.dto.CategoryDtoRequest;
import by.clevertec.dto.CategoryDtoResponse;
import by.clevertec.enums.category.CarCategory;
import by.clevertec.exception.CarNotFoundException;
import by.clevertec.exception.CategoryNotFoundException;
import by.clevertec.mapper.CategoryMapper;
import by.clevertec.models.Category;
import by.clevertec.repositories.CategoryRepository;
import by.clevertec.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryServices {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public void create(@Validated CategoryDtoRequest categoryDtoRequest) {
        Category category = categoryMapper.toCategory(categoryDtoRequest);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CarNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CategoryDtoResponse update(CategoryDtoRequest categoryDtoRequest, Long id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            Optional.ofNullable(categoryDtoRequest.getCarCategory())
                    .map(carCategory -> CarCategory.valueOf(carCategory.toUpperCase()))
                    .ifPresent(category::setCarCategory);
            categoryRepository.save(category);

            CategoryDtoResponse categoryDtoResponse = categoryMapper.toCategoryDtoResponse(category);

            System.out.println(categoryDtoResponse.getCarCategory()+ "<------------");
            return categoryMapper.toCategoryDtoResponse(category);
        } else {
            log.error("Category not found with id: " + id);
            throw new CategoryNotFoundException("Category not found with id: " + id);
        }
    }
}
