package com.example.where2go.service.impl;

import com.example.where2go.converter.FeatureConverter;
import com.example.where2go.entity.Feature;
import com.example.where2go.entity.User;
import com.example.where2go.model.FeatureModel;
import com.example.where2go.repository.FeatureRepository;
import com.example.where2go.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureConverter featureConverter;

    @Override
    public FeatureModel createFeature(FeatureModel featureModel) {
        featureRepository.save(featureConverter.convertFromModel(featureModel));
        return featureModel;
    }

    @Override
    public Page<Feature> getPage(Pageable pageable) {
        return featureRepository.findAll(pageable);
    }


    @Override
    public FeatureModel getById(Long id) {
        return featureConverter.convertFromEntity(featureRepository.findById(id).orElse(null));
    }

    @Override
    public FeatureModel updateFeature(FeatureModel featureModel) {
        FeatureModel featureModelForUpdate = getById(featureConverter.convertFromModel(featureModel).getId());

        if (featureModel.getFeature() != null) featureModelForUpdate.setFeature(featureModel.getFeature());
        if (featureModel.getEstablishmentId() != null) featureModelForUpdate.setEstablishmentId(featureModel.getEstablishmentId());

        featureRepository.save(featureConverter.convertFromModel(featureModelForUpdate));
        return featureModelForUpdate;
    }

    @Override
    public FeatureModel deleteById(Long id) {
        FeatureModel featureModelForDelete = getById(id);
        if (featureModelForDelete != null) featureRepository.delete(featureConverter.convertFromModel(featureModelForDelete));

        return featureModelForDelete;
    }
}
