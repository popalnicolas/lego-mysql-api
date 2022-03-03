package com.example.legomysqlapi.Webservice.Service.Interface;

import com.example.legomysqlapi.Model.UserModel;

public interface IUserService {
    UserModel registerUser(UserModel user);
    UserModel getUser(String userEmail);
    void addRoleToUser(String userEmail, String roleName);
    UserModel getUserFromHeader(String header);
}
