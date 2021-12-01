package com.example.where2go.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstablishmentModel {
    private String name;
    private String address;
    private String workSchedule;
    private Long userId;
    private Long categoryId;
}
