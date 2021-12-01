package com.example.where2go.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_date")
    private LocalDateTime createDate;
    @Column(name = "updated_date")
    private LocalDateTime updateDate;

    @PrePersist
    private void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}