package com.example.where2go.boot;

import com.example.where2go.converter.EstablishmentConverter;
import com.example.where2go.converter.UserConverter;
import com.example.where2go.entity.User;
import com.example.where2go.entity.UserRole;
import com.example.where2go.model.*;
import com.example.where2go.repository.CategoryRepository;
import com.example.where2go.repository.EstablishmentRepository;
import com.example.where2go.repository.UserRepository;
import com.example.where2go.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements CommandLineRunner {
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public void run(String... args) throws Exception {

//        UserRoleModel admin = new UserRoleModel();
//        admin.setRoleName("ROLE_ADMIN");
//        userRoleService.createUserRole(admin);
//        UserRoleModel client = new UserRoleModel();
//        client.setRoleName("ROLE_CLIENT");
//        userRoleService.createUserRole(client);

    }
}
