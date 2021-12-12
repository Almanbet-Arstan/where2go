package com.example.where2go.converter;

import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.EstablishmentTable;
import com.example.where2go.model.EstablishmentTableModel;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentTableConverter extends BaseConverter<EstablishmentTableModel, EstablishmentTable>{
    public EstablishmentTableConverter() {
        super(EstablishmentTableConverter::convertToEntity, EstablishmentTableConverter::convertToModel);
    }

    private static EstablishmentTableModel convertToModel(EstablishmentTable entityToConvert){
        if (entityToConvert == null) return null;
        return EstablishmentTableModel.builder()
                .seats(entityToConvert.getSeats())
                .establishmentId(entityToConvert.getEstablishment().getId())
                .build();
    }

    private static EstablishmentTable convertToEntity(EstablishmentTableModel modelToConvert){
        if (modelToConvert == null) return null;

        EstablishmentTable tableToReturn = new EstablishmentTable();

        tableToReturn.setSeats(modelToConvert.getSeats());

        Establishment establishment = new Establishment();
        establishment.setId(modelToConvert.getEstablishmentId());

        tableToReturn.setEstablishment(establishment);

        return tableToReturn;
    }
}
