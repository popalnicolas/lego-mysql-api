package com.example.legomysqlapi.Repository;

import com.example.legomysqlapi.Model.UserRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRoleRepository extends JpaRepository<UserRoleModel, Long> {
    UserRoleModel findByRoleName(String roleName);
}
