package com.example.legomysqlapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lego")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LegoModel {
    @Id
    @Column(name = "legoId")
    private long legoId;

    @Column(name = "legoName")
    private String legoName;

    @Column(name = "age")
    private short age;

    @Column(name = "legoImage", columnDefinition = "text")
    private String legoImage;

    @Column(name = "legoManual", columnDefinition = "text")
    private String legoManual;

    @Column(name = "price")
    private float price;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryModel category;
}
