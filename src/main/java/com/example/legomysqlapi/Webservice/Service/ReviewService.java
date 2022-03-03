package com.example.legomysqlapi.Webservice.Service;

import com.example.legomysqlapi.Model.ReviewModel;
import com.example.legomysqlapi.Repository.IReviewRepository;
import com.example.legomysqlapi.Webservice.Service.Interface.IReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewService implements IReviewService {

    private final IReviewRepository reviewRepository;

    @Override
    public void leaveReview(ReviewModel reviewModel) {
        if(reviewRepository.findReviewModelByUser_UserIdAndLego_LegoId(reviewModel.getUser().getUserId(), reviewModel.getLego().getLegoId()) == null) {
            log.info("Reviewing lego {} with rating {} by user {}", reviewModel.getLego().getLegoName(), reviewModel.getRating(), reviewModel.getUser().getUserEmail());
            reviewRepository.save(reviewModel);
        }
    }

    @Override
    public List<ReviewModel> getReviewByLegoId(long legoId) {
        log.info("Getting reviews for a lego with id {}", legoId);
        return reviewRepository.findAllByLego_LegoId(legoId);
    }

    @Override
    public List<ReviewModel> getAllByUserId(long userId) {
        log.info("Getting all reviews for a user with id {}", userId);
        return reviewRepository.findAllByUser_UserId(userId);
    }
}
