package com.example.legomysqlapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private long categoryId;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "categoryImage", columnDefinition = "text")
    private String categoryImage;

    @Column(name = "categoryColor")
    private String categoryColor;
}
