package com.example.where2go.repository;

import com.example.where2go.entity.Image;
import com.example.where2go.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
