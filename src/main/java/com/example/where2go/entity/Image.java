package com.example.where2go.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseEntity{
    @Column(name = "url")
    private String url;
}
