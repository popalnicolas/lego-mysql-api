package com.example.legomysqlapi.Repository;

import com.example.legomysqlapi.Model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryModel, Long> {
}
