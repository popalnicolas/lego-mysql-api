package com.example.legomysqlapi.Repository;

import com.example.legomysqlapi.Model.LegoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILegoRepository extends JpaRepository<LegoModel, Long> {
    List<LegoModel> findAllByCategory_CategoryId(long category_categoryId);
    List<LegoModel> findAllByAgeBetween(short age, short age2);
    List<LegoModel> findTop2ByOrderByLegoIdDesc();
}
