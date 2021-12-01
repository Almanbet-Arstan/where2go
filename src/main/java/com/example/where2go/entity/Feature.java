package com.example.where2go.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "features")
@Getter
@Setter
@NoArgsConstructor
public class Feature extends BaseEntity{
    @Column(name = "feature")
    private String feature;

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
}
