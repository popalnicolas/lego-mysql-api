package com.example.legomysqlapi.Webservice.Service.Interface;

import com.example.legomysqlapi.Model.ReviewModel;
import com.example.legomysqlapi.Model.UserModel;

import java.util.List;

public interface IReviewService {
    void leaveReview(ReviewModel reviewModel, UserModel user);
    List<ReviewModel> getReviewByLegoId(long legoId);
    List<ReviewModel> getAllByUserId(long userId);
}
