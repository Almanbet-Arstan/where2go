package com.example.where2go.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingModel {
    private String bookingTimeFrom;
    private String bookingTimeTill;
    private Long userId;
    private Long tableId;
}
