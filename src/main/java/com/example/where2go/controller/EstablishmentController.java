package com.example.where2go.controller;

import com.example.where2go.model.EstablishmentModel;
import com.example.where2go.service.EstablishmentService;
import com.example.where2go.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establishment")
public class EstablishmentController {
    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping
    public List<EstablishmentModel> getAll(){
        return establishmentService.getAll();
    }

    @GetMapping("/{id}")
    public EstablishmentModel getById(@PathVariable Long id){
        return establishmentService.getById(id);
    }

    @PostMapping
    public EstablishmentModel createEstablishment(@RequestBody EstablishmentModel establishmentModel){
        return establishmentService.createEstablishment(establishmentModel);
    }

    @PutMapping
    public EstablishmentModel updateEstablishment(@RequestBody EstablishmentModel establishmentModel){
        return establishmentService.updateEstablishment(establishmentModel);
    }

    @DeleteMapping("/{id}")
    public EstablishmentModel deleteById(@PathVariable Long id){
        return establishmentService.deleteById(id);
    }
}
