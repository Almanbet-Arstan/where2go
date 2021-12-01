package com.example.where2go.model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeatureModel {
    private String feature;
    private Long establishmentId;
}
