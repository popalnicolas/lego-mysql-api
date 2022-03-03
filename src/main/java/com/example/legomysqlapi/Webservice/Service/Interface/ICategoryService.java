package com.example.legomysqlapi.Webservice.Service.Interface;

import com.example.legomysqlapi.Model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    void createCategory(CategoryModel category);
    boolean categoryExists(CategoryModel category);
    List<CategoryModel> getAllCategories();
}
