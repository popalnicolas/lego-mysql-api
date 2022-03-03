package com.example.legomysqlapi.Repository;

import com.example.legomysqlapi.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUserEmail(String userEmail);
}
