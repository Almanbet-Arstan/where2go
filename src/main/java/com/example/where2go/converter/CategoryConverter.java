package com.example.where2go.converter;

import com.example.where2go.entity.Category;
import com.example.where2go.entity.Category;
import com.example.where2go.model.CategoryModel;
import com.example.where2go.model.CategoryModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends BaseConverter<CategoryModel, Category>{
    public CategoryConverter() {
        super(CategoryConverter::convertToEntity, CategoryConverter::convertToModel);
    }

    private static CategoryModel convertToModel(Category entityToConvert){
        if (entityToConvert == null) return null;
        return CategoryModel.builder()
                .name(entityToConvert.getName())
                .build();
    }

    private static Category convertToEntity(CategoryModel modelToConvert){
        if (modelToConvert == null) return null;

        Category categoryToReturn = new Category();

        categoryToReturn.setName(modelToConvert.getName());

        return categoryToReturn;
    }
}
