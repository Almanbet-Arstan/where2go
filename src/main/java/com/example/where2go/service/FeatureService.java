package com.example.where2go.service;

import com.example.where2go.entity.Feature;
import com.example.where2go.entity.User;
import com.example.where2go.model.FeatureModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeatureService {
    FeatureModel createFeature(FeatureModel featureModel);
    FeatureModel getById(Long id);
    FeatureModel updateFeature(FeatureModel featureModel);
    FeatureModel deleteById(Long id);
    Page<FeatureModel> getPage(Pageable pageable);
}
