package com.example.where2go.boot;

import com.example.where2go.entity.UserRole;
import com.example.where2go.model.UserRoleModel;
import com.example.where2go.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartRunner implements CommandLineRunner {
    @Autowired
    private UserRoleService userRoleService;


    @Override
    public void run(String... args) throws Exception {

        UserRoleModel admin = new UserRoleModel();
        admin.setRoleName("ROLE_ADMIN");
        userRoleService.createUserRole(admin);
        UserRoleModel client = new UserRoleModel();
        client.setRoleName("ROLE_CLIENT");
        userRoleService.createUserRole(client);
    }
}
