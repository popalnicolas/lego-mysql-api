package com.example.legomysqlapi.Webservice.Api;

import com.example.legomysqlapi.Model.LegoModel;
import com.example.legomysqlapi.Model.ReviewModel;
import com.example.legomysqlapi.Model.UserModel;
import com.example.legomysqlapi.Webservice.Service.Interface.IReviewService;
import com.example.legomysqlapi.Webservice.Service.Interface.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lego/review")
public class ReviewController {

    private final IReviewService reviewService;
    private final IUserService userService;

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<List<ReviewModel>> getReviewByLegoId(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(reviewService.getReviewByLegoId(id));
    }

    @GetMapping
    public ResponseEntity<List<ReviewModel>> getReviewsForUser(@RequestHeader("Authorization") String header)
    {
        UserModel user = userService.getUserFromHeader(header);
        return ResponseEntity.ok().body(reviewService.getAllByUserId(user.getUserId()));
    }

    @PostMapping
    public ResponseEntity leaveReview(@RequestHeader("Authorization") String header, @RequestBody ReviewModel reviewModel)
    {
        UserModel user = userService.getUserFromHeader(header);
        reviewService.leaveReview(reviewModel, user);
        return ResponseEntity.ok().build();
    }
}
