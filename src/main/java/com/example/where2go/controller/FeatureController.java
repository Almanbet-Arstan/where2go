package com.example.where2go.controller;

import com.example.where2go.model.FeatureModel;
import com.example.where2go.service.FeatureService;
import com.example.where2go.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feature")
public class FeatureController {
    @Autowired
    private FeatureService featureService;

    @GetMapping
    public List<FeatureModel> getAll(){
        return featureService.getAll();
    }

    @GetMapping("/{id}")
    public FeatureModel getById(@PathVariable Long id){
        return featureService.getById(id);
    }

    @PostMapping
    public FeatureModel createFeature(@RequestBody FeatureModel featureModel){
        return featureService.createFeature(featureModel);
    }

    @PutMapping
    public FeatureModel updateFeature(@RequestBody FeatureModel featureModel){
        return featureService.updateFeature(featureModel);
    }

    @DeleteMapping("/{id}")
    public FeatureModel deleteById(@PathVariable Long id){
        return featureService.deleteById(id);
    }
}
