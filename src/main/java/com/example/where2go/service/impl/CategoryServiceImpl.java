package com.example.where2go.service.impl;

import com.example.where2go.converter.CategoryConverter;
import com.example.where2go.converter.FeatureConverter;
import com.example.where2go.entity.Category;
import com.example.where2go.entity.Establishment;
import com.example.where2go.model.CategoryModel;
import com.example.where2go.model.EstablishmentModel;
import com.example.where2go.repository.CategoryRepository;
import com.example.where2go.repository.FeatureRepository;
import com.example.where2go.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;


    @Override
    public CategoryModel createCategory(CategoryModel categoryModel) {
        categoryRepository.save(categoryConverter.convertFromModel(categoryModel));
        return categoryModel;
    }

    @Override
    public List<CategoryModel> getAll() {
        List<CategoryModel> categoryModels = new ArrayList<>();
        for (Category category:categoryRepository.findAll()) {
            categoryModels.add(categoryConverter.convertFromEntity(category));
        }
        return categoryModels;
    }

    @Override
    public CategoryModel getById(Long id) {
        return categoryConverter.convertFromEntity(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public CategoryModel updateCategory(CategoryModel categoryModel) {
        CategoryModel categoryModelForUpdate = getById(categoryConverter.convertFromModel(categoryModel).getId());

        if (categoryModel.getName() != null) categoryModelForUpdate.setName(categoryModel.getName());

        categoryRepository.save(categoryConverter.convertFromModel(categoryModelForUpdate));
        return categoryModelForUpdate;
    }

    @Override
    public CategoryModel deleteById(Long id) {
        CategoryModel categoryModelForDelete = getById(id);
        if (categoryModelForDelete != null) categoryRepository.delete(categoryConverter.convertFromModel(categoryModelForDelete));

        return categoryModelForDelete;
    }
}
