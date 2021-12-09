package com.example.where2go.converter;

import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.Image;
import com.example.where2go.model.ImageModel;
import org.springframework.stereotype.Component;


@Component
public class ImageConverter extends BaseConverter<ImageModel, Image>{
    public ImageConverter() {
        super(ImageConverter::convertToEntity, ImageConverter::convertToModel);
    }

    private static ImageModel convertToModel(Image entityToConvert){
        if (entityToConvert == null) return null;
        return ImageModel.builder()
                .id(entityToConvert.getId())
                .url(entityToConvert.getUrl())
                .build();
    }

    private static Image convertToEntity(ImageModel modelToConvert){
        if (modelToConvert == null) return null;

        Image imageToReturn = new Image();

        imageToReturn.setId(modelToConvert.getId());
        imageToReturn.setUrl(modelToConvert.getUrl());

        return imageToReturn;
    }
}
