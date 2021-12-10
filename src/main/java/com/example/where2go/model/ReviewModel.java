package com.example.where2go.model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewModel {
    private String review;
    private Long establishmentId;
    private Long userId;
}
