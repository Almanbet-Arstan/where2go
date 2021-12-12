package com.example.where2go.model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingModel {
    private String bookingTime;
    private Long userId;
    private Long tableId;
}
