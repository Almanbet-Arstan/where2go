package com.example.where2go.service;

import com.example.where2go.entity.User;
import com.example.where2go.model.UserAuthModel;
import com.example.where2go.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserModel createUser(UserModel userModel);
    Page<UserModel> getPage(Pageable pageable);
    UserModel getById(Long id);
    UserModel updateUser(UserModel userModel);
    UserModel deleteById(Long id);
    String getBasicAuthHeaderByAuthModel(UserAuthModel userAuthModel);
    User getCurrentUser();
}
