package com.example.where2go.controller;

import com.example.where2go.model.UserAuthModel;
import com.example.where2go.model.UserModel;
import com.example.where2go.model.UserRoleModel;
import com.example.where2go.service.UserRoleService;
import com.example.where2go.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserModel getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel userModel){
        return userService.createUser(userModel);
    }

    @PutMapping
    public UserModel updateUser(@RequestBody UserModel userModel){
        return userService.updateUser(userModel);
    }

    @DeleteMapping("/{id}")
    public UserModel deleteById(@PathVariable Long id){
        return userService.deleteById(id);
    }

    @PostMapping("/sign-in")
    public String getAuthHead(@RequestBody UserAuthModel userAuthModel){
        return userService.getBasicAuthHeaderByAuthModel(userAuthModel);
    }
}
