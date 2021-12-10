package com.example.where2go.controller;

import com.example.where2go.entity.User;
import com.example.where2go.model.UserAuthModel;
import com.example.where2go.model.UserModel;
import com.example.where2go.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserModel> getPage(Pageable pageable) {
        return userService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public UserModel getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping("/get-current")
    public User getCurrentUser(){
        return userService.getCurrentUser();
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
