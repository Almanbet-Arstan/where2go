package com.example.where2go.service;

import com.example.where2go.model.EstablishmentModel;

import java.util.List;

public interface EstablishmentService {
    EstablishmentModel createEstablishment(EstablishmentModel establishmentModel);
    List<EstablishmentModel> getAll();
    EstablishmentModel getById(Long id);
    EstablishmentModel updateEstablishment(EstablishmentModel establishmentModel);
    EstablishmentModel deleteById(Long id);
}
