package com.example.legomysqlapi.Webservice.Api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.legomysqlapi.Model.UserModel;
import com.example.legomysqlapi.Model.UserRoleModel;
import com.example.legomysqlapi.Security.SecurityConfig;
import com.example.legomysqlapi.Webservice.Service.Interface.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserModel user)
    {
        if(userService.getUser(user.getUserEmail()) == null)
        {
            userService.registerUser(user);
            userService.addRoleToUser(user.getUserEmail(), "ROLE_USER");
            user.setUserPassword("");
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email address already exists");
    }

    @PutMapping("/avatar")
    public ResponseEntity changeAvatar(@RequestHeader("Authorization") String header, @RequestParam("avatarId") long avatarId)
    {
        UserModel user = userService.getUserFromHeader(header);
        userService.changeAvatar(user, avatarId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserModel> getUser(@RequestHeader("Authorization") String header)
    {
        return ResponseEntity.ok().body(userService.getUserFromHeader(header));
    }
}
