package by.clevertec.services.impl;

import by.clevertec.factoryes.CategoryFactory;
import by.clevertec.models.Car;
import by.clevertec.models.Category;
import by.clevertec.services.CategoryServices;
import by.clevertec.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryServices {
    @Override
    public void create() {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Category categoryUpdate, Long id) {

    }
}
