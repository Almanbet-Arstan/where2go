package com.example.where2go.converter;

import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.Feature;
import com.example.where2go.model.FeatureModel;
import org.springframework.stereotype.Component;


@Component
public class FeatureConverter extends BaseConverter<FeatureModel, Feature> {
    public FeatureConverter() {
        super(FeatureConverter::convertToEntity, FeatureConverter::convertToModel);
    }

    private static FeatureModel convertToModel(Feature entityToConvert){
        if (entityToConvert == null) return null;
        return FeatureModel.builder()
                .feature(entityToConvert.getFeature())
                .establishmentId(entityToConvert.getEstablishment().getId())
                .build();
    }

    private static Feature convertToEntity(FeatureModel modelToConvert){
        if (modelToConvert == null) return null;

        Feature featureToReturn = new Feature();

        featureToReturn.setFeature(modelToConvert.getFeature());
        Establishment establishment = new Establishment();
        establishment.setId(modelToConvert.getEstablishmentId());
        featureToReturn.setEstablishment(establishment);

        return featureToReturn;
    }
}
