package com.example.where2go.controller;

import com.example.where2go.model.EstablishmentImageModel;
import com.example.where2go.service.EstablishmentImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establishment-image")
public class EstablishmentImageController {

    @Autowired
    private EstablishmentImageService establishmentImageService;

    @GetMapping
    public List<EstablishmentImageModel> getAll(){
        return establishmentImageService.getAll();
    }

    @GetMapping("/{id}")
    public EstablishmentImageModel getById(@PathVariable Long id){
        return establishmentImageService.getById(id);
    }

    @PostMapping
    public EstablishmentImageModel createEstablishment(@RequestBody EstablishmentImageModel establishmentImageModel){
        return establishmentImageService.createEstablishmentImage(establishmentImageModel);
    }

    @PutMapping
    public EstablishmentImageModel updateEstablishment(@RequestBody EstablishmentImageModel establishmentImageModel){
        return establishmentImageService.updateEstablishmentImage(establishmentImageModel);
    }

    @DeleteMapping("/{id}")
    public EstablishmentImageModel deleteById(@PathVariable Long id){
        return establishmentImageService.deleteById(id);
    }
}
