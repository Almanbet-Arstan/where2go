package com.example.where2go.service.impl;

import com.example.where2go.converter.EstablishmentConverter;
import com.example.where2go.entity.Establishment;
import com.example.where2go.model.EstablishmentModel;
import com.example.where2go.repository.EstablishmentRepository;
import com.example.where2go.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private EstablishmentConverter establishmentConverter;

    @Override
    public EstablishmentModel createEstablishment(EstablishmentModel establishmentModel) {
        establishmentRepository.save(establishmentConverter.convertFromModel(establishmentModel));
        return establishmentModel;
    }

    @Override
    public List<EstablishmentModel> getAll() {
        List<EstablishmentModel> establishmentModels = new ArrayList<>();
        for (Establishment establishment:establishmentRepository.findAll()) {
            establishmentModels.add(establishmentConverter.convertFromEntity(establishment));
        }
        return establishmentModels;
    }

    @Override
    public EstablishmentModel getById(Long id) {
        return establishmentConverter.convertFromEntity(establishmentRepository.findById(id).orElse(null));
    }

    @Override
    public EstablishmentModel updateEstablishment(EstablishmentModel establishmentModel) {
        EstablishmentModel establishmentModelForUpdate = getById(establishmentConverter.convertFromModel(establishmentModel).getId());

        if (establishmentModel.getName() != null) establishmentModelForUpdate.setName(establishmentModel.getName());
        if (establishmentModel.getAddress() != null) establishmentModelForUpdate.setAddress(establishmentModel.getAddress());
        if (establishmentModel.getWorkSchedule() != null) establishmentModelForUpdate.setWorkSchedule(establishmentModel.getWorkSchedule());
        if (establishmentModel.getCategoryId() != null) establishmentModelForUpdate.setCategoryId(establishmentModel.getCategoryId());
        if (establishmentModel.getUserId() != null) establishmentModelForUpdate.setUserId(establishmentModel.getUserId());

        establishmentRepository.save(establishmentConverter.convertFromModel(establishmentModelForUpdate));
        return establishmentModelForUpdate;
    }

    @Override
    public EstablishmentModel deleteById(Long id) {
        EstablishmentModel establishmentModelForDelete = getById(id);
        if (establishmentModelForDelete != null) establishmentRepository.delete(establishmentConverter.convertFromModel(establishmentModelForDelete));

        return establishmentModelForDelete;
    }
}
