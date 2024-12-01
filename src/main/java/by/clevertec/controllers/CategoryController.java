package by.clevertec.controllers;

import by.clevertec.factory.CategoryFactory;
import by.clevertec.services.CategoryServices;
import by.clevertec.services.impl.CategoryServiceImpl;

import java.util.List;

public class CategoryController {
    public static void main(String[] args) {
        CategoryServices categoryServices = new CategoryServiceImpl();

        //add category
//        categoryServices.addCategory();

        //delete category
//        categoryServices.deleteCategory(1l);

        //update category
//        categoryServices.updateCategory(CategoryFactory.getCategory(), 2L);

        //add category in car
        List<Long> listCarIdJoinWitnCategoryId = List.of(10L);
        categoryServices.linkCategoryWithCars(2l, listCarIdJoinWitnCategoryId);
    }
}
