package com.example.where2go.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserRole extends BaseEntity{
    @Column(name = "role_name")
    private String roleName;
}
