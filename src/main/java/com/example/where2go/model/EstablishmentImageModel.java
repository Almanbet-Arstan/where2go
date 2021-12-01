package com.example.where2go.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstablishmentImageModel {
    private Long imageId;
    private Long establishmentId;
}
