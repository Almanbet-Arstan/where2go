package com.example.where2go.service.impl;

import com.example.where2go.converter.CategoryConverter;
import com.example.where2go.converter.FeatureConverter;
import com.example.where2go.entity.Category;
import com.example.where2go.entity.Establishment;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.CategoryModel;
import com.example.where2go.model.EstablishmentModel;
import com.example.where2go.repository.CategoryRepository;
import com.example.where2go.repository.FeatureRepository;
import com.example.where2go.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if (categoryModel.getName().isEmpty()) throw new ApiException("Введите название категории", HttpStatus.BAD_REQUEST);
        categoryRepository.save(categoryConverter.convertFromModel(categoryModel));
        return categoryModel;
    }

    @Override
    public List<CategoryModel> getAll() {
        List<CategoryModel> categoryModels = new ArrayList<>();
        for (Category category:categoryRepository.findAll()) {
            categoryModels.add(categoryConverter.convertFromEntity(category));
        }
        if (categoryModels.isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return categoryModels;
    }

    @Override
    public CategoryModel getById(Long id) {
        CategoryModel categoryModel = categoryConverter.convertFromEntity(categoryRepository.findById(id).orElse(null));
        if (categoryModel == null) throw new ApiException("Не нашли категорию по id " + id, HttpStatus.BAD_REQUEST);
        return categoryModel;
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
