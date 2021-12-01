package com.example.where2go.controller;

import com.example.where2go.model.ImageModel;
import com.example.where2go.service.ImageService;
import com.example.where2go.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<ImageModel> getAll() {
        return imageService.getAll();
    }

    @PostMapping
    public ImageModel saveImage(@RequestParam(name = "file") MultipartFile file) {
        return imageService.saveImage(file);
    }
}
