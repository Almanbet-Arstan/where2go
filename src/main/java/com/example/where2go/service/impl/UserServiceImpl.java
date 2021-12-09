package com.example.where2go.service.impl;

import com.example.where2go.converter.UserConverter;
import com.example.where2go.entity.User;
import com.example.where2go.exceptions.ApiException;
import com.example.where2go.model.UserAuthModel;
import com.example.where2go.model.UserModel;
import com.example.where2go.repository.UserRepository;
import com.example.where2go.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_LOGIN_REGEX =
            Pattern.compile("^[a-zA-Z0-9._-]{3,}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserModel createUser(UserModel userModel) {
        Matcher email = VALID_EMAIL_ADDRESS_REGEX.matcher(userModel.getEmail());
        Matcher password = VALID_PASSWORD_REGEX.matcher(userModel.getPassword());
        Matcher login = VALID_LOGIN_REGEX.matcher(userModel.getLogin());
        if (!login.find()) throw new ApiException("Логин слишком короткий", HttpStatus.BAD_REQUEST);
        if (!password.find()) throw new ApiException("Пароль должен содержать буквенные и численные символы, не должен быть меньше 8 символов, а также не иметь пробелов", HttpStatus.BAD_REQUEST);
        if (!email.find()) throw new ApiException("Введите правильный email", HttpStatus.BAD_REQUEST);
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        userModel.setUserRoleId(2L);
        userModel.setIsActive(1L);
        userRepository.save(userConverter.convertFromModel(userModel));
        return userModel;
    }

    @Override
    public Page<UserModel> getPage(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);

        if (userPage.getContent().isEmpty())
            throw new ApiException("Список пустой", HttpStatus.BAD_REQUEST);
        return userPage.map(userConverter::convertFromEntity);
    }

    @Override
    public UserModel getById(Long id) {
        UserModel userModel = userConverter.convertFromEntity(userRepository.findById(id).orElse(null));
        if (userModel == null) throw new ApiException("Не нашли клиента под id: " + id, HttpStatus.BAD_REQUEST);
        return userModel;
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        UserModel userModelForUpdate = getById(userConverter.convertFromModel(userModel).getId());

        if (userModel.getLogin() != null) userModelForUpdate.setLogin(userModel.getLogin());
        if (userModel.getPassword() != null) userModelForUpdate.setPassword(userModel.getPassword());
        if (userModel.getEmail() != null) userModelForUpdate.setEmail(userModel.getEmail());
        if (userModel.getUserRoleId() != null) userModelForUpdate.setUserRoleId(userModel.getUserRoleId());
        if (userModel.getIsActive() != null) userModelForUpdate.setIsActive(userModel.getIsActive());

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

        if (user == null) throw new ApiException("Неправильный логин", HttpStatus.BAD_REQUEST);

        String encryptedPasswordFromDb = user.getPassword();

        boolean isPasswordCorrect = passwordEncoder.matches(userAuthModel.getPassword(), encryptedPasswordFromDb);

        if (!isPasswordCorrect) throw new ApiException("Неправильный пароль", HttpStatus.BAD_REQUEST);

        String fullNamePasswordPair = userAuthModel.getLogin() + ":" + userAuthModel.getPassword();

        String authHeader = new String(Base64.getEncoder().encode(fullNamePasswordPair.getBytes()));

        return "Basic " + authHeader;
    }
}
