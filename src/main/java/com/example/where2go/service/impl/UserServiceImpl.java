package com.example.where2go.service.impl;

import com.example.where2go.converter.UserConverter;
import com.example.where2go.entity.User;
import com.example.where2go.model.UserAuthModel;
import com.example.where2go.model.UserModel;
import com.example.where2go.repository.UserRepository;
import com.example.where2go.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserModel createUser(UserModel userModel) {
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        userRepository.save(userConverter.convertFromModel(userModel));
        return userModel;
    }

    @Override
    public List<UserModel> getAll() {
        List<UserModel> userModels = new ArrayList<>();
        for (User user:userRepository.findAll()) {
            userModels.add(userConverter.convertFromEntity(user));
        }
        return userModels;
    }

    @Override
    public UserModel getById(Long id) {
        return userConverter.convertFromEntity(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        UserModel userModelForUpdate = getById(userConverter.convertFromModel(userModel).getId());

        if (userModel.getLogin() != null) userModelForUpdate.setLogin(userModel.getLogin());
        if (userModel.getPassword() != null) userModelForUpdate.setPassword(userModel.getPassword());
        if (userModel.getEmail() != null) userModelForUpdate.setEmail(userModel.getEmail());
        if (userModel.getUserRoleId() != null) userModelForUpdate.setUserRoleId(userModel.getUserRoleId());

        userRepository.save(userConverter.convertFromModel(userModelForUpdate));
        return userModelForUpdate;
    }

    @Override
    public UserModel deleteById(Long id) {
        UserModel userModelForDelete = getById(id);
        if (userModelForDelete != null) userRepository.delete(userConverter.convertFromModel(userModelForDelete));

        return userModelForDelete;
    }

    @Override
    public String getBasicAuthHeaderByAuthModel(UserAuthModel userAuthModel) {
        User user = userRepository.getByLogin(userAuthModel.getLogin())
                .orElse(null);

        String encryptedPasswordFromDb = user.getPassword();

        boolean isPasswordCorrect = passwordEncoder.matches(userAuthModel.getPassword(), encryptedPasswordFromDb);

        if (!isPasswordCorrect) return null;

        String fullNamePasswordPair = userAuthModel.getLogin() + ":" + userAuthModel.getPassword();

        String authHeader = new String(Base64.getEncoder().encode(fullNamePasswordPair.getBytes()));

        return "Basic " + authHeader;
    }
}
