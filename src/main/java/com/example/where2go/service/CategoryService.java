package com.example.where2go.service;

import com.example.where2go.entity.Category;
import com.example.where2go.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    CategoryModel createCategory(CategoryModel categoryModel);
    List<CategoryModel> getAll();
    CategoryModel getById(Long id);
    CategoryModel updateCategory(CategoryModel categoryModel);
    CategoryModel deleteById(Long id);
}
