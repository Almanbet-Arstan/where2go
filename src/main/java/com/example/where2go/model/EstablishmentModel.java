package com.example.where2go.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstablishmentModel {
    private String name;
    private String address;
    private String workScheduleFrom;
    private String workScheduleTill;
    private Long userId;
    private Long categoryId;
}
