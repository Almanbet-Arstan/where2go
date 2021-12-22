package com.example.where2go.service.impl;

import com.example.where2go.converter.FeatureConverter;
import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.Feature;
import com.example.where2go.entity.User;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.FeatureModel;
import com.example.where2go.repository.EstablishmentRepository;
import com.example.where2go.repository.FeatureRepository;
import com.example.where2go.service.FeatureService;
import com.example.where2go.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureConverter featureConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Override
    public FeatureModel createFeature(FeatureModel featureModel) {
        if (featureModel.getFeature().isEmpty()) throw new ApiException("Введите особенность", HttpStatus.BAD_REQUEST);
        if (featureModel.getEstablishmentId() == null) throw new ApiException("Введите id заведения", HttpStatus.BAD_REQUEST);
        if (!establishmentRepository.findEstablishmentById(featureModel.getEstablishmentId()).orElse(null).getUser().getId().equals(userService.getCurrentUser().getId())) throw new ApiException("Вы не можете вносить изменения в это заведение", HttpStatus.BAD_REQUEST);;
        List<Feature> features = featureRepository.findFeaturesByEstablishmentId(featureModel.getEstablishmentId());
        for (Feature feature:features) {
            if (feature.getFeature().toUpperCase(Locale.ROOT).equals(featureModel.getFeature().toUpperCase(Locale.ROOT))) throw new ApiException("Такая особенность уже существует", HttpStatus.BAD_REQUEST);
        }
        featureRepository.save(featureConverter.convertFromModel(featureModel));
        return featureModel;
    }

    @Override
    public Page<FeatureModel> getPage(Pageable pageable) {
        Page<Feature> featurePage = featureRepository.findAll(pageable);
        if (featurePage.getContent().isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return featurePage.map(featureConverter::convertFromEntity);
    }


    @Override
    public FeatureModel getById(Long id) {
        FeatureModel featureModel = featureConverter.convertFromEntity(featureRepository.findById(id).orElse(null));
        if (featureModel == null) throw new ApiException("Не нашли особенность по id " + id, HttpStatus.BAD_REQUEST);
        return featureModel;
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
