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

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(SecurityConfig.secretKey);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserModel userModel = userService.getUser(username);

                String access_token = JWT.create()
                        .withSubject(userModel.getUserEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) //1 day token
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", userModel.getRoles().stream().map(UserRoleModel::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e){
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else
        {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
