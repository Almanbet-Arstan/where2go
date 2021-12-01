package com.example.where2go.converter;

import com.example.where2go.entity.*;
import com.example.where2go.entity.EstablishmentImage;
import com.example.where2go.model.EstablishmentImageModel;
import com.example.where2go.model.EstablishmentImageModel;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentImageConverter extends BaseConverter<EstablishmentImageModel, EstablishmentImage>{
    public EstablishmentImageConverter() {
        super(EstablishmentImageConverter::convertToEntity, EstablishmentImageConverter::convertToModel);
    }

    private static EstablishmentImageModel convertToModel(EstablishmentImage entityToConvert){
        if (entityToConvert == null) return null;
        return EstablishmentImageModel.builder()
                .imageId(entityToConvert.getImage().getId())
                .establishmentId(entityToConvert.getEstablishment().getId())
                .build();
    }

    private static EstablishmentImage convertToEntity(EstablishmentImageModel modelToConvert){
        if (modelToConvert == null) return null;

        EstablishmentImage establishmentImageToReturn = new EstablishmentImage();

        Image image = new Image();
        image.setId(modelToConvert.getImageId());
        establishmentImageToReturn.setImage(image);
        Establishment establishment = new Establishment();
        establishment.setId(modelToConvert.getEstablishmentId());
        establishmentImageToReturn.setEstablishment(establishment);

        return establishmentImageToReturn;
    }
}
