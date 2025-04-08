package com.tablemaster_api.controller;

import com.tablemaster_api.dto.LeaveReviewDto;
import com.tablemaster_api.dto.ReviewDto;
import com.tablemaster_api.entity.Review;
import com.tablemaster_api.service.ReviewService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Void> leaveReview(@RequestBody LeaveReviewDto review, @PathVariable long restaurantId) {
        reviewService.leaveReview(review,restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<ReviewDto>> getAllReviews(@PathVariable long restaurantId,
                                                         @RequestParam(required = false) String sortBy,
                                                         @RequestParam(required = false) String orderBy) {
        Sort sort = Sort.by(Sort.Order.by(sortBy));
        if (orderBy.equalsIgnoreCase("desc")) {
            sort.descending();
        } else {
            sort.ascending();
        }
        return ResponseEntity.ok(reviewService.getAllSorted(restaurantId, sort));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable long reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<Void> updateReview(@PathVariable("reviewId") long reviewId,
                                             @RequestBody LeaveReviewDto reviewDto) {
        reviewService.updateReview(reviewId,reviewDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}
