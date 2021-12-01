package com.example.where2go.converter;

import com.example.where2go.entity.UserRole;
import com.example.where2go.entity.UserRole;
import com.example.where2go.model.UserRoleModel;
import com.example.where2go.model.UserRoleModel;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter extends BaseConverter<UserRoleModel, UserRole>{
    public UserRoleConverter() {
        super(UserRoleConverter::convertToEntity, UserRoleConverter::convertToModel);
    }

    private static UserRoleModel convertToModel(UserRole entityToConvert){
        if (entityToConvert == null) return null;
        return UserRoleModel.builder()
                .roleName(entityToConvert.getRoleName())
                .build();
    }

    private static UserRole convertToEntity(UserRoleModel modelToConvert){
        if (modelToConvert == null) return null;

        UserRole userRoleToReturn = new UserRole();

        userRoleToReturn.setRoleName(modelToConvert.getRoleName());

        return userRoleToReturn;
    }
}
