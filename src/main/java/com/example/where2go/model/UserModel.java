package com.example.where2go.model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private String login;
    private String password;
    private String email;
    private Long userRoleId;
}
