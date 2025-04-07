package com.tablemaster_api.controller;

import com.tablemaster_api.dto.ReviewDto;
import com.tablemaster_api.entity.Review;
import com.tablemaster_api.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews/")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Void> leaveReview(@RequestBody Review review) {
        reviewService.leaveReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{restaurantId}")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable int restaurantId) {
        return ResponseEntity.ok(reviewService.getAllReviews(restaurantId));
    }

    @GetMapping("restaurant/{restaurantId}/review/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable int restaurantId, @PathVariable int reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(restaurantId,reviewId));
    }

}
