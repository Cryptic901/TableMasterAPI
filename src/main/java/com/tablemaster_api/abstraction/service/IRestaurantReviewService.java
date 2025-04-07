package com.tablemaster_api.abstraction.service;

import com.tablemaster_api.dto.RestaurantReviewDto;
import com.tablemaster_api.entity.RestaurantReview;

import java.util.List;

public interface IRestaurantReviewService {

    String leaveRestaurantReview(RestaurantReview review);

    List<RestaurantReviewDto> getAllRestaurantReviews(long restaurantId);

    RestaurantReview getRestaurantReviewById(long restaurantId, long reviewId);

    RestaurantReviewDto updateRestaurantReview(long reviewId, RestaurantReviewDto restaurantReviewDto);

    String deleteRestaurantReview(long reviewId);

    List<RestaurantReviewDto> orderRestaurantReviewsByRatingDesc(long restaurantId);

    List<RestaurantReviewDto> orderRestaurantReviewsByRatingAsc(long restaurantId);

    List<RestaurantReviewDto> orderRestaurantReviewsByTimeDesc(long restaurantId);

    List<RestaurantReviewDto> orderRestaurantReviewsByTimeAsc(long restaurantId);
}
