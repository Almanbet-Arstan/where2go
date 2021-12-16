package com.example.where2go.model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstablishmentTableModel {
    private Integer seats;
    private Long establishmentId;
    private Integer count;
}
