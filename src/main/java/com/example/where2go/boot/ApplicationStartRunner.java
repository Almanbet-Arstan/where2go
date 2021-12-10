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

    @Autowired
    private UserService userService;

    @Autowired
    private EstablishmentService establishmentService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FeatureService featureService;

    @Autowired ReviewService reviewService;


    @Override
    public void run(String... args) throws Exception {

        UserRoleModel admin = new UserRoleModel();
        admin.setRoleName("ROLE_ADMIN");
        userRoleService.createUserRole(admin);
        UserRoleModel client = new UserRoleModel();
        client.setRoleName("ROLE_CLIENT");
        userRoleService.createUserRole(client);

//        for (int i = 0; i < 20; i++) {
//            userService.createUser(UserModel.builder()
//                    .login("user" + i)
//                    .password("password" + i)
//                    .email("astan" + i + "@mail.ru")
//                    .userRoleId(2L)
//                    .build());
//        }
//
//        CategoryModel cafe = new CategoryModel();
//        cafe.setName("name");
//        categoryService.createCategory(cafe);
//        CategoryModel bar = new CategoryModel();
//        bar.setName("bar");
//        categoryService.createCategory(bar);
//
//        for (int i = 0; i < 10; i++) {
//            establishmentService.createEstablishment(EstablishmentModel.builder()
//                    .name("name" + i)
//                    .address("address" + i)
//                    .workSchedule("19:0" + i + "-04:00")
//                    .userId(1L)
//                    .categoryId(1L)
//                    .build());
//        }
//        for (int i = 10; i < 20; i++) {
//            establishmentService.createEstablishment(EstablishmentModel.builder()
//                    .name("name" + i)
//                    .address("address" + i)
//                    .workSchedule("19:0" + i + "-04:00")
//                    .userId(2L)
//                    .categoryId(2L)
//                    .build());
//        }
//        for (int i = 0; i < 10; i++) {
//            featureService.createFeature(FeatureModel.builder()
//                    .establishmentId(1L)
//                    .feature("вкусная еда")
//                    .build());
//        }
//        for (int i = 10; i < 20; i++) {
//            featureService.createFeature(FeatureModel.builder()
//                    .establishmentId(2L)
//                    .feature("отличная музыка")
//                    .build());
//        }
//
//        for (int i = 0; i < 10; i++) {
//            reviewService.createReview(ReviewModel.builder()
//                    .review("Дерьмовое место и цены высокие")
//                    .establishmentId(1L)
//                    .userId(1L)
//                    .build());
//        }
    }
}
