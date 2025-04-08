package com.tablemaster_api.abstraction.service;

import com.tablemaster_api.dto.LeaveReviewDto;
import com.tablemaster_api.dto.ReviewDto;
import com.tablemaster_api.entity.Review;

import java.util.List;

public interface IReviewService {

    void leaveReview(LeaveReviewDto userReview, long restaurantId);

    List<ReviewDto> getAllReviews(long restaurantId);

    Review getReviewById(long reviewId);

    void updateReview(long reviewId, LeaveReviewDto reviewDto);

    void deleteReview(long reviewId);

}
