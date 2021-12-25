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
import com.example.where2go.service.UserService;
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

    @Autowired
    private UserService userService;

    @Override
    public EstablishmentModel createEstablishment(EstablishmentModel establishmentModel) {
        if (establishmentModel.getName().isEmpty()) throw new ApiException("Введите имя", HttpStatus.BAD_REQUEST);
        if (establishmentModel.getAddress().isEmpty()) throw new ApiException("Введите адрес", HttpStatus.BAD_REQUEST);
        if (establishmentModel.getWorkScheduleFrom() == null) throw new ApiException("Введите время работы от", HttpStatus.BAD_REQUEST);
        if (establishmentModel.getWorkScheduleTill() == null) throw new ApiException("Введите время работы до", HttpStatus.BAD_REQUEST);
        if (establishmentModel.getCategoryId() == null) throw new ApiException("Введите категорию", HttpStatus.BAD_REQUEST);
        List<Establishment> establishments = establishmentRepository.findEstablishmentsByName(establishmentModel.getName());
        for (Establishment establishment:establishments) {
            if (establishment.getAddress().equals(establishmentModel.getAddress())) throw new ApiException("По такому адресу уже существует " + establishmentModel.getName(), HttpStatus.BAD_REQUEST);
        }
        establishmentModel.setUserId(userService.getCurrentUser().getId());
        establishmentRepository.save(establishmentConverter.convertFromModel(establishmentModel));
        return establishmentModel;
    }

    @Override
    public EstablishmentModel getById(Long id) {
        EstablishmentModel establishmentModel = establishmentConverter.convertFromEntity(establishmentRepository.findById(id).orElse(null));
        if (establishmentModel == null) throw new ApiException("Не нашли заведение по id " + id, HttpStatus.BAD_REQUEST);
        return establishmentModel;
    }

    @Override
    public EstablishmentModel updateEstablishment(EstablishmentModel establishmentModel) {
        EstablishmentModel establishmentModelForUpdate = getById(establishmentConverter.convertFromModel(establishmentModel).getId());

        if (establishmentModel.getName() != null) establishmentModelForUpdate.setName(establishmentModel.getName());
        if (establishmentModel.getAddress() != null) establishmentModelForUpdate.setAddress(establishmentModel.getAddress());
        if (establishmentModel.getWorkScheduleFrom() != null) establishmentModelForUpdate.setWorkScheduleFrom(establishmentModel.getWorkScheduleFrom());
        if (establishmentModel.getWorkScheduleTill() != null) establishmentModelForUpdate.setWorkScheduleTill(establishmentModel.getWorkScheduleTill());
        if (establishmentModel.getUserId() != null) establishmentModelForUpdate.setUserId(establishmentModel.getUserId());
        if (establishmentModel.getCategoryId() != null) establishmentModelForUpdate.setCategoryId(establishmentModel.getCategoryId());
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
    public Page<EstablishmentModel> getPage(Pageable pageable) {
        Page<Establishment> establishmentPage = establishmentRepository.findAll(pageable);
        if (establishmentPage.getContent().isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return establishmentPage.map(establishmentConverter::convertFromEntity);
    }

    @Override
    public Page<EstablishmentModel> getPageSortedByCategory(Long id, Pageable pageable) {
        Page<Establishment> establishmentPage = establishmentRepository.findAllByCategoryId(id, pageable);
        if (establishmentPage.getContent().isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return establishmentPage.map(establishmentConverter::convertFromEntity);
    }

    @Override
    public Page<EstablishmentModel> getSortedPage(EstablishmentModel establishmentModel, Pageable pageable) {
        EstablishmentSpecification establishmentSpecification = new EstablishmentSpecification(establishmentModel);
        Page<Establishment> establishmentPage = establishmentRepository.findAll(establishmentSpecification, pageable);
        if (establishmentPage.getContent().isEmpty()) throw new ApiException("Список пуст", HttpStatus.BAD_REQUEST);
        return establishmentPage.map(establishmentConverter::convertFromEntity);
    }

    @Override
    public ApiException saveImages(List<MultipartFile> images, Long establishmentId) {
        if (establishmentId == null) throw new ApiException("Введите id заведения", HttpStatus.BAD_REQUEST);
        if (images.isEmpty()) throw new ApiException("Картинок нету", HttpStatus.BAD_REQUEST);
        for (MultipartFile image:images) {
            EstablishmentImageModel establishmentImageModel = new EstablishmentImageModel();
            ImageModel imageModel = imageService.saveImage(image);
            establishmentImageModel.setImageId(imageModel.getId());
            establishmentImageModel.setEstablishmentId(establishmentId);
            establishmentImageService.createEstablishmentImage(establishmentImageModel);
        }
        return new ApiException("Успешно сохранено", HttpStatus.OK);
    }
}
