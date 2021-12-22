package com.example.where2go.repository;

import com.example.where2go.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findFeaturesByEstablishmentId(Long id);
}
