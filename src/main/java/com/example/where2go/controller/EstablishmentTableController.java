package com.example.where2go.controller;

import com.example.where2go.model.EstablishmentTableModel;
import com.example.where2go.service.EstablishmentTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
public class EstablishmentTableController {
    @Autowired
    private EstablishmentTableService establishmentTableService;

    @GetMapping
    public List<EstablishmentTableModel> getAll(){
        return establishmentTableService.getAll();
    }

    @GetMapping("/{id}")
    public EstablishmentTableModel getById(@PathVariable Long id){
        return establishmentTableService.getById(id);
    }

    @PostMapping
    public EstablishmentTableModel createCategory(@RequestBody EstablishmentTableModel categoryModel){
        return establishmentTableService.createTable(categoryModel);
    }

    @PutMapping
    public EstablishmentTableModel updateCategory(@RequestBody EstablishmentTableModel categoryModel){
        return establishmentTableService.updateTable(categoryModel);
    }

    @DeleteMapping("/{id}")
    public EstablishmentTableModel deleteById(@PathVariable Long id){
        return establishmentTableService.deleteById(id);
    }
}
