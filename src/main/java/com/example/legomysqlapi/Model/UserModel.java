package com.example.legomysqlapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private long userId;

    @Column(name = "email")
    private String userEmail;

    @Column(name = "password")
    private String userPassword;

    @ManyToOne
    @JoinColumn(name = "avatar")
    private AvatarModel avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<UserRoleModel> roles = new ArrayList<>();
}
