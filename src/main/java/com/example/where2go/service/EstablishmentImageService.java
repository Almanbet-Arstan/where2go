package com.example.where2go.service;

import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.EstablishmentImageModel;

import java.util.List;

public interface EstablishmentImageService {
    ApiException createEstablishmentImage(EstablishmentImageModel establishmentImageModel);
    List<EstablishmentImageModel> getAll();
    EstablishmentImageModel getById(Long id);
    EstablishmentImageModel updateEstablishmentImage(EstablishmentImageModel establishmentImageModel);
    EstablishmentImageModel deleteById(Long id);
}
