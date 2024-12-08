package by.clevertec.services.impl;

import by.clevertec.dto.CategoryDtoRequest;
import by.clevertec.mapper.CategoryMapper;
import by.clevertec.models.Category;
import by.clevertec.repositories.CategoryRepository;
import by.clevertec.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

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

    }

    @Override
    @Transactional
    public void update(Category categoryUpdate, Long id) {

    }
}
