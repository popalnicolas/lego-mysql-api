package com.example.legomysqlapi.Model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class ReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private long reviewId;

    @Column(name = "rating")
    private short rating;

    @Column(name = "comment", columnDefinition = "text")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIncludeProperties({"userId", "userEmail", "avatar"})
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "legoId")
    private LegoModel lego;
}
