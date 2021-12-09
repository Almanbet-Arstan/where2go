package com.example.where2go.service.impl;

import com.example.where2go.converter.UserConverter;
import com.example.where2go.converter.UserRoleConverter;
import com.example.where2go.entity.User;
import com.example.where2go.entity.UserRole;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.UserModel;
import com.example.where2go.model.UserRoleModel;
import com.example.where2go.repository.UserRepository;
import com.example.where2go.repository.UserRoleRepository;
import com.example.where2go.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleConverter userRoleConverter;

    @Override
    public UserRoleModel createUserRole(UserRoleModel userRoleModel) {
        if (userRoleModel.getRoleName().isEmpty()) throw new ApiException("Введите название роли", HttpStatus.BAD_REQUEST);
        userRoleRepository.save(userRoleConverter.convertFromModel(userRoleModel));
        return userRoleModel;
    }

    @Override
    public List<UserRoleModel> getAll() {
        List<UserRoleModel> userRoleModels = new ArrayList<>();
        for (UserRole userRole:userRoleRepository.findAll()) {
            userRoleModels.add(userRoleConverter.convertFromEntity(userRole));
        }
        if (userRoleModels.isEmpty()) throw new ApiException("Список пустой", HttpStatus.BAD_REQUEST);
        return userRoleModels;
    }

    @Override
    public UserRoleModel getById(Long id) {
        UserRoleModel userRoleModel = userRoleConverter.convertFromEntity(userRoleRepository.findById(id).orElse(null));
        if (userRoleModel == null) throw new ApiException("Не нашли роль по id " + id, HttpStatus.BAD_REQUEST);
        return userRoleModel;
    }

    @Override
    public UserRoleModel updateUserRole(UserRoleModel userRoleModel) {
        UserRoleModel userRoleModelForUpdate = getById(userRoleConverter.convertFromModel(userRoleModel).getId());

        if (userRoleModel.getRoleName() != null) userRoleModelForUpdate.setRoleName(userRoleModel.getRoleName());

        userRoleRepository.save(userRoleConverter.convertFromModel(userRoleModelForUpdate));
        return userRoleModelForUpdate;
    }

    @Override
    public UserRoleModel deleteById(Long id) {
        UserRoleModel userRoleModelForDelete = getById(id);
        if (userRoleModelForDelete != null) userRoleRepository.delete(userRoleConverter.convertFromModel(userRoleModelForDelete));

        return userRoleModelForDelete;
    }
}
