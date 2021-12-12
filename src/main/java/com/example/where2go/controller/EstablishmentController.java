package com.example.where2go.controller;

import com.example.where2go.entity.Establishment;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.EstablishmentModel;
import com.example.where2go.service.EstablishmentService;
import com.example.where2go.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/establishment")
public class EstablishmentController {
    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping
    public Page<EstablishmentModel> getPage(Pageable pageable){
        return establishmentService.getPage(pageable);
    }

    @PostMapping("/pageable")
    public Page<EstablishmentModel> getSortedPage(@RequestBody EstablishmentModel establishmentModel, Pageable pageable) {
        return establishmentService.getSortedPage(establishmentModel, pageable);
    }

    @GetMapping("/category/{id}")
    public Page<EstablishmentModel> getPageSortedByCategory(@PathVariable Long id, Pageable pageable){
        return establishmentService.getPageSortedByCategory(id, pageable);
    }

    @GetMapping("/{id}")
    public EstablishmentModel getById(@PathVariable Long id){
        return establishmentService.getById(id);
    }

    @PostMapping
    public EstablishmentModel createEstablishment(@RequestBody EstablishmentModel establishmentModel){
        return establishmentService.createEstablishment(establishmentModel);
    }

    @PostMapping("/images")
    public ApiException saveImages(@RequestParam List<MultipartFile> images,
                                   @RequestParam Long establishmentId){
        return establishmentService.saveImages(images, establishmentId);
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
