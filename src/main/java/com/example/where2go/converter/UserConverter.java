package com.example.where2go.converter;

import com.example.where2go.entity.User;
import com.example.where2go.entity.UserRole;
import com.example.where2go.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConverter<UserModel, User>{
    public UserConverter() {
        super(UserConverter::convertToEntity, UserConverter::convertToModel);
    }

    private static UserModel convertToModel(User entityToConvert){
        if (entityToConvert == null) return null;
        return UserModel.builder()
                .login(entityToConvert.getLogin())
                .password(entityToConvert.getPassword())
                .email(entityToConvert.getEmail())
                .isActive(entityToConvert.getIsActive())
                .userRoleId(entityToConvert.getUserRole().getId())
                .build();
    }

    private static User convertToEntity(UserModel modelToConvert){
        if (modelToConvert == null) return null;

        User userToReturn = new User();

        userToReturn.setLogin(modelToConvert.getLogin());
        userToReturn.setPassword(modelToConvert.getPassword());
        userToReturn.setEmail(modelToConvert.getEmail());
        userToReturn.setIsActive(modelToConvert.getIsActive());
        UserRole userRole = new UserRole();
        userRole.setId(modelToConvert.getUserRoleId());
        userToReturn.setUserRole(userRole);

        return userToReturn;
    }
}
