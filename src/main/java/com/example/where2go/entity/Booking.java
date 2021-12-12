package com.example.where2go.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity{

    @Column(name = "booking_time")
    private String bookingTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "establishment_table_id")
    private EstablishmentTable establishmentTable;
}
