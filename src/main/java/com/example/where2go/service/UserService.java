package com.example.where2go.service;

import com.example.where2go.entity.User;
import com.example.where2go.model.UserAuthModel;
import com.example.where2go.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel createUser(UserModel userModel);
    List<UserModel> getAll();
    UserModel getById(Long id);
    UserModel updateUser(UserModel userModel);
    UserModel deleteById(Long id);
    String getBasicAuthHeaderByAuthModel(UserAuthModel userAuthModel);
}
