package com.example.where2go.service;

import com.example.where2go.model.EstablishmentImageModel;

import java.util.List;

public interface EstablishmentImageService {
    EstablishmentImageModel createEstablishmentImage(EstablishmentImageModel establishmentImageModel);
    List<EstablishmentImageModel> getAll();
    EstablishmentImageModel getById(Long id);
    EstablishmentImageModel updateEstablishmentImage(EstablishmentImageModel establishmentImageModel);
    EstablishmentImageModel deleteById(Long id);
}
