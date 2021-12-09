package com.example.where2go.repository;


import com.example.where2go.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> getByLogin(String login);
}
