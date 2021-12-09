package com.example.where2go.service.impl;

import com.example.where2go.converter.EstablishmentConverter;
import com.example.where2go.entity.Establishment;
import com.example.where2go.entity.Image;
import com.example.where2go.entity.User;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.EstablishmentImageModel;
import com.example.where2go.model.EstablishmentModel;
import com.example.where2go.model.ImageModel;
import com.example.where2go.repository.EstablishmentRepository;
import com.example.where2go.service.EstablishmentImageService;
import com.example.where2go.service.EstablishmentService;
import com.example.where2go.service.ImageService;
import com.example.where2go.specification.EstablishmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private EstablishmentConverter establishmentConverter;

    @Autowired
    private ImageService imageService;

    @Autowired
    private EstablishmentImageService establishmentImageService;

    @Override
    public EstablishmentModel createEstablishment(EstablishmentModel establishmentModel) {
        establishmentRepository.save(establishmentConverter.convertFromModel(establishmentModel));
        return establishmentModel;
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

    @Override
    public Page<Establishment> getPage(Pageable pageable) {
        return establishmentRepository.findAll(pageable);
    }

    @Override
    public Page<Establishment> getSortedPage(EstablishmentModel establishmentModel, Pageable pageable) {
        EstablishmentSpecification establishmentSpecification = new EstablishmentSpecification(establishmentModel);
        return establishmentRepository.findAll(establishmentSpecification, pageable);
    }

    @Override
    public ApiException saveImages(List<MultipartFile> images, Long establishmentId) {
        for (MultipartFile image:images) {
            EstablishmentImageModel establishmentImageModel = new EstablishmentImageModel();
            ImageModel i = imageService.saveImage(image);
            establishmentImageModel.setImageId(i.getId());
            establishmentImageModel.setEstablishmentId(establishmentId);
            establishmentImageService.createEstablishmentImage(establishmentImageModel);
        }
        return new ApiException("Успешно сохранено", HttpStatus.OK);
    }
}
