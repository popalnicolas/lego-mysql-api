package com.example.legomysqlapi.Webservice.Service.Interface;

import com.example.legomysqlapi.Model.ReviewModel;

import java.util.List;

public interface IReviewService {
    void leaveReview(ReviewModel reviewModel);
    List<ReviewModel> getReviewByLegoId(long legoId);
    List<ReviewModel> getAllByUserId(long userId);
}
