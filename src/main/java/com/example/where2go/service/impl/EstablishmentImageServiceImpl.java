package com.example.where2go.service.impl;

import com.example.where2go.converter.EstablishmentImageConverter;
import com.example.where2go.entity.EstablishmentImage;
import com.example.where2go.entity.Image;
import com.example.where2go.entity.User;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.EstablishmentImageModel;
import com.example.where2go.repository.EstablishmentImageRepository;
import com.example.where2go.repository.ImageRepository;
import com.example.where2go.service.EstablishmentImageService;
import com.example.where2go.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstablishmentImageServiceImpl implements EstablishmentImageService {

    @Autowired
    private EstablishmentImageRepository establishmentImageRepository;

    @Autowired
    private EstablishmentImageConverter establishmentImageConverter;


    @Override
    public ApiException createEstablishmentImage(EstablishmentImageModel establishmentImageModel) {
        establishmentImageRepository.save(establishmentImageConverter.convertFromModel(establishmentImageModel));
        return new ApiException("Все успешно сохранилось", HttpStatus.OK);
    }

    @Override
    public List<EstablishmentImageModel> getAll() {
        List<EstablishmentImageModel> establishmentImageModels = new ArrayList<>();
        for (EstablishmentImage establishmentImage:establishmentImageRepository.findAll()) {
            establishmentImageModels.add(establishmentImageConverter.convertFromEntity(establishmentImage));
        }
        return establishmentImageModels;
    }

    @Override
    public EstablishmentImageModel getById(Long id) {
        return establishmentImageConverter.convertFromEntity(establishmentImageRepository.findById(id).orElse(null));
    }

    @Override
    public EstablishmentImageModel updateEstablishmentImage(EstablishmentImageModel establishmentImageModel) {
        EstablishmentImageModel establishmentImageModelForUpdate = getById(establishmentImageConverter.convertFromModel(establishmentImageModel).getId());

        if (establishmentImageModel.getEstablishmentId() != null) establishmentImageModelForUpdate.setEstablishmentId(establishmentImageModel.getEstablishmentId());
        if (establishmentImageModel.getImageId() != null) establishmentImageModelForUpdate.setImageId(establishmentImageModel.getImageId());

        establishmentImageRepository.save(establishmentImageConverter.convertFromModel(establishmentImageModelForUpdate));
        return establishmentImageModelForUpdate;
    }

    @Override
    public EstablishmentImageModel deleteById(Long id) {
        EstablishmentImageModel establishmentImageModelForDelete = getById(id);
        if (establishmentImageModelForDelete != null) establishmentImageRepository.delete(establishmentImageConverter.convertFromModel(establishmentImageModelForDelete));

        return establishmentImageModelForDelete;
    }

//    @PostMapping("/upload-image")
//    public User saveImageForUser(@RequestParam List<MultipartFile> multipartFiles,
//                                 @RequestParam String userBody) throws JsonProcessingException {
//        System.out.println(multipartFiles.size());
//        ObjectMapper objectMapper = new ObjectMapper();
//        User user = objectMapper.readValue(userBody, User.class);
//        return user;
//
//    }
}
