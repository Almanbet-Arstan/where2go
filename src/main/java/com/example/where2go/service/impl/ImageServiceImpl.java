package com.example.where2go.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.where2go.converter.ImageConverter;
import com.example.where2go.entity.Feature;
import com.example.where2go.entity.Image;
import com.example.where2go.model.EstablishmentImageModel;
import com.example.where2go.model.FeatureModel;
import com.example.where2go.model.ImageModel;
import com.example.where2go.repository.ImageRepository;
import com.example.where2go.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageConverter imageConverter;


    @Override
    public List<ImageModel> getAll() {
        List<ImageModel> imageModels = new ArrayList<>();
        for (Image image:imageRepository.findAll()) {
            imageModels.add(imageConverter.convertFromEntity(image));
        }
        return imageModels;
    }

    private static final String CLOUDINARY_URL = "cloudinary://441976649634836:AMuIYGRLtbXOVImWP1K0HiL4TvM@dkxnbt14i";

    @Override
    public String saveImageInCloudinary(MultipartFile multipartFile) {

        File file;

        try {
            file = Files.createTempFile(System.currentTimeMillis() + "",
                    multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length() - 4)).toFile();
            multipartFile.transferTo(file);

            Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

            return ((String) uploadResult.get("url"));


        } catch (IOException e) {
            System.out.println("ImageService.createImage: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ImageModel saveImage(String url) {
        ImageModel image = new ImageModel();
        image.setUrl(url);
        imageRepository.save(imageConverter.convertFromModel(image));
        return image;
    }

    @Override
    public ImageModel saveImage(MultipartFile multipartFile) {
        String imageUrl = saveImageInCloudinary(multipartFile);
        return saveImage(imageUrl);
//        public EstablishmentImageModel addingImages(ImagesForAddingModel imagesForAddingModel) {
//
//            EstablishmentImageModel establishmentImageModel = new EstablishmentImageModel();
//
//            for(Image image : imagesForAddingModel.getImages()) {
//                imageService.saveImage(image.getUrl());
//                establishmentImageModel.setEstablishmentId(imagesForAddingModel.getEstablishmentId());
//                establishmentImageModel.setImageId(image.getId());
//                establishmentImageRepository.save(establishmentImageConverter.convertFromModel(establishmentImageModel));
//            }
//
//            return establishmentImageModel;
//        }
    }
}
