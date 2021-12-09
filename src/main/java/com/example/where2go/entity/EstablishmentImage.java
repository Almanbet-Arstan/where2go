package com.example.where2go.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "establishment_images")
@Getter
@Setter
@NoArgsConstructor
public class EstablishmentImage extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;
}
