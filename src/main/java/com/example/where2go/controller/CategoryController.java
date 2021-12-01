package com.example.where2go.controller;

import com.example.where2go.model.CategoryModel;
import com.example.where2go.service.CategoryService;
import com.example.where2go.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryModel> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryModel getById(@PathVariable Long id){
        return categoryService.getById(id);
    }

    @PostMapping
    public CategoryModel createCategory(@RequestBody CategoryModel categoryModel){
        return categoryService.createCategory(categoryModel);
    }

    @PutMapping
    public CategoryModel updateCategory(@RequestBody CategoryModel categoryModel){
        return categoryService.updateCategory(categoryModel);
    }

    @DeleteMapping("/{id}")
    public CategoryModel deleteById(@PathVariable Long id){
        return categoryService.deleteById(id);
    }
}
