package com.example.where2go.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstablishmentTable extends BaseEntity{
    @Column(name = "seats")
    private Integer seats;

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
}
