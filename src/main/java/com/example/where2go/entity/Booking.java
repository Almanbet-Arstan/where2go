package com.example.where2go.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity{

    @Column(name = "booking_time_from")
    private LocalDateTime bookingTimeFrom;

    @Column(name = "booking_time_till")
    private LocalDateTime bookingTimeTill;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "establishment_table_id")
    private EstablishmentTable establishmentTable;
}
