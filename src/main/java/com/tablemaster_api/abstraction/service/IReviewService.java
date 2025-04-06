package com.tablemaster_api.abstraction.service;

import com.tablemaster_api.dto.ReviewDto;
import com.tablemaster_api.entity.Review;

import java.util.List;

public interface IReviewService {

    String leaveReview(Review review);

    List<ReviewDto> getAllReviews(long restaurantId);

    Review getReviewById(long restaurantId, long reviewId);

    ReviewDto updateReview(long reviewId, ReviewDto restaurantReviewDto);

    String deleteReview(long reviewId);

    List<ReviewDto> orderReviewsByRatingDesc(long restaurantId);

    List<ReviewDto> orderReviewsByRatingAsc(long restaurantId);

    List<ReviewDto> orderReviewsByTimeDesc(long restaurantId);

    List<ReviewDto> orderReviewsByTimeAsc(long restaurantId);
}
