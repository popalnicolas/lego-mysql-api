package com.example.legomysqlapi.Webservice.Service;

import com.example.legomysqlapi.Model.AvatarModel;
import com.example.legomysqlapi.Model.UserModel;
import com.example.legomysqlapi.Model.UserRoleModel;
import com.example.legomysqlapi.Repository.IAvatarRepository;
import com.example.legomysqlapi.Repository.IUserRepository;
import com.example.legomysqlapi.Repository.IUserRoleRepository;
import com.example.legomysqlapi.Webservice.Service.Interface.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;
    private final IUserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final IAvatarRepository avatarRepository;

    @Override
    public UserModel registerUser(UserModel user) {
        log.info("Saving new user {} to the database", user.getUserEmail());
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setAvatar(avatarRepository.getById(1L));
        return userRepository.save(user);
    }

    @Override
    public UserModel getUser(String userEmail) {
        log.info("Getting user {} from database", userEmail);
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public void addRoleToUser(String userEmail, String roleName) {
        log.info("Adding role {} to user {}", roleName, userEmail);
        UserModel user = userRepository.findByUserEmail(userEmail);
        UserRoleModel role = userRoleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserModel getUserFromHeader(String header) {
        String[] payload = header.split("\\.");
        String payloadDecode = new String (Base64.getUrlDecoder().decode(payload[1]));
        String[] emailArray = payloadDecode.split("\"");
        String email = emailArray[3];
        UserModel user = getUser(email);
        log.info("Getting {} user information from database", email);
        return user;
    }

    @Override
    public void changeAvatar(UserModel user, long avatarId) {
        AvatarModel avatar = avatarRepository.getById(avatarId);
        log.info("Changing users {} avatar", user.getUserEmail());
        user.setAvatar(avatar);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUserEmail(username);
        if(user == null)
        {
            log.error("User with email {} not found in the database", username);
            throw new UsernameNotFoundException("User not found in the database");
        }
        else
            log.info("User found in the database: {}", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new User(user.getUserEmail(), user.getUserPassword(), authorities);
    }
}
