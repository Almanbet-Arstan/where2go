package com.example.where2go.service;

import com.example.where2go.model.UserRoleModel;

import java.util.List;

public interface UserRoleService {
    UserRoleModel createUserRole(UserRoleModel userRoleModel);
    List<UserRoleModel> getAll();
    UserRoleModel getById(Long id);
    UserRoleModel updateUserRole(UserRoleModel userRoleModel);
    UserRoleModel deleteById(Long id);
}
