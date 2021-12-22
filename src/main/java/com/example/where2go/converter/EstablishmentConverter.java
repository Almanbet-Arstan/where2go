package com.example.where2go.converter;

import com.example.where2go.entity.Category;
import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.User;
import com.example.where2go.model.EstablishmentModel;
import com.example.where2go.model.EstablishmentModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EstablishmentConverter extends BaseConverter<EstablishmentModel, Establishment>{
    public EstablishmentConverter() {
        super(EstablishmentConverter::convertToEntity, EstablishmentConverter::convertToModel);
    }

    private static EstablishmentModel convertToModel(Establishment entityToConvert){
        if (entityToConvert == null) return null;
        return EstablishmentModel.builder()
                .name(entityToConvert.getName())
                .address(entityToConvert.getAddress())
                .workScheduleFrom(entityToConvert.getWorkScheduleFrom())
                .workScheduleTill(entityToConvert.getWorkScheduleTill())
                .userId(entityToConvert.getUser().getId())
                .categoryId(entityToConvert.getCategory().getId())
                .build();
    }

    private static Establishment convertToEntity(EstablishmentModel modelToConvert){
        if (modelToConvert == null) return null;

        Establishment establishmentToReturn = new Establishment();

        establishmentToReturn.setName(modelToConvert.getName());
        establishmentToReturn.setAddress(modelToConvert.getAddress());
        establishmentToReturn.setWorkScheduleFrom(modelToConvert.getWorkScheduleFrom());
        establishmentToReturn.setWorkScheduleTill(modelToConvert.getWorkScheduleTill());
        User user = new User();
        user.setId(modelToConvert.getUserId());
        establishmentToReturn.setUser(user);
        Category category = new Category();
        category.setId(modelToConvert.getCategoryId());
        establishmentToReturn.setCategory(category);

        return establishmentToReturn;
    }
}
