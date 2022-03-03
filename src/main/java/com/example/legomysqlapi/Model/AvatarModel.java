package com.example.legomysqlapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "avatars")
public class AvatarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avatarId")
    private long avatarId;

    @Column(name = "avatarImage", columnDefinition = "text")
    private String avatarImage;
}
