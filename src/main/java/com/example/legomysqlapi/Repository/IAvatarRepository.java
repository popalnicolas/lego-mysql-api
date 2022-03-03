package com.example.legomysqlapi.Repository;

import com.example.legomysqlapi.Model.AvatarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAvatarRepository extends JpaRepository<AvatarModel, Long> {
}
