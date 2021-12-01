package com.example.where2go.service;

import com.example.where2go.model.FeatureModel;

import java.util.List;

public interface FeatureService {
    FeatureModel createFeature(FeatureModel featureModel);
    List<FeatureModel> getAll();
    FeatureModel getById(Long id);
    FeatureModel updateFeature(FeatureModel featureModel);
    FeatureModel deleteById(Long id);
}
