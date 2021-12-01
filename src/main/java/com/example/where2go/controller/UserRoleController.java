package com.example.where2go.controller;

import com.example.where2go.model.UserRoleModel;
import com.example.where2go.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public List<UserRoleModel> getAll(){
        return userRoleService.getAll();
    }

    @GetMapping("/{id}")
    public UserRoleModel getById(@PathVariable Long id){
        return userRoleService.getById(id);
    }

    @PostMapping
    public UserRoleModel createUserRole(@RequestBody UserRoleModel userRoleModel){
        return userRoleService.createUserRole(userRoleModel);
    }

    @PutMapping
    public UserRoleModel updateUserRole(@RequestBody UserRoleModel userRoleModel){
        return userRoleService.updateUserRole(userRoleModel);
    }

    @DeleteMapping("/{id}")
    public UserRoleModel deleteById(@PathVariable Long id){
        return userRoleService.deleteById(id);
    }
}
