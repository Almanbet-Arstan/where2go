package com.example.where2go.service;

import com.example.where2go.model.ImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<ImageModel> getAll();
    String saveImageInCloudinary(MultipartFile multipartFile);
    ImageModel saveImage(String url);

    ImageModel saveImage(MultipartFile multipartFile);
}
