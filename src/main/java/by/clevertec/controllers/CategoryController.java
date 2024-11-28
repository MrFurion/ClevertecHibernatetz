package by.clevertec.controllers;

import by.clevertec.factory.CategoryFactory;
import by.clevertec.services.CategoryServices;
import by.clevertec.services.impl.CategoryServiceImpl;

public class CategoryController {
    public static void main(String[] args) {
        CategoryServices categoryServices = new CategoryServiceImpl();

        //add category
//        categoryServices.addCategory(CategoryFactory.getCategory());

        //delete category
//        categoryServices.deleteCategory(1l);

        //update category
//        categoryServices.updateCategory(CategoryFactory.getCategory(), 2L);
    }
}
