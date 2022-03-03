package com.example.legomysqlapi.Repository;

import com.example.legomysqlapi.Model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReviewRepository extends JpaRepository<ReviewModel, Long> {
    List<ReviewModel> findAllByLego_LegoId(long lego_legoId);
    List<ReviewModel> findAllByUser_UserId(long user_userId);
    ReviewModel findReviewModelByUser_UserIdAndLego_LegoId(long user_userId, long lego_legoId);
}
