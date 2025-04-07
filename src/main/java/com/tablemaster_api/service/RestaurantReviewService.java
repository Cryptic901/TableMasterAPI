package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.RestaurantReviewRepository;
import com.tablemaster_api.abstraction.service.IRestaurantReviewService;
import com.tablemaster_api.dto.RestaurantReviewDto;
import com.tablemaster_api.entity.RestaurantReview;
import com.tablemaster_api.mapper.RestaurantReviewDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantReviewService implements IRestaurantReviewService {

    private final RestaurantReviewRepository restaurantReviewRepository;
    private final RestaurantReviewDtoMapper restaurantReviewDtoMapper;


    public RestaurantReviewService(RestaurantReviewRepository restaurantReviewRepository, RestaurantReviewDtoMapper restaurantReviewDtoMapper) {
        this.restaurantReviewRepository = restaurantReviewRepository;
        this.restaurantReviewDtoMapper = restaurantReviewDtoMapper;
    }

    public String leaveRestaurantReview(RestaurantReview review) {
        try {
            restaurantReviewRepository.save(review);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "The review was left successfully";
    }

    public List<RestaurantReviewDto> getAllRestaurantReviews(long restaurantId) {
        return restaurantReviewRepository.findRestaurantReviewByRestaurantId(restaurantId)
                .stream().map(restaurantReviewDtoMapper::fromEntity).toList();
    }

    public RestaurantReview getRestaurantReviewById(long restaurantId, long reviewId) {
        return restaurantReviewRepository.findRestaurantReviewByRestaurantId(restaurantId)
                .stream().filter(r -> r.getId().equals(reviewId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    public RestaurantReviewDto updateRestaurantReview(long reviewId, RestaurantReviewDto restaurantReviewDto) {
        RestaurantReview review = restaurantReviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        restaurantReviewDtoMapper.updateEntityFromDto(restaurantReviewDto, review);
        restaurantReviewRepository.save(review);
        return restaurantReviewDtoMapper.fromEntity(review);
    }

    public String deleteRestaurantReview(long reviewId) {
        RestaurantReview review = restaurantReviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        restaurantReviewRepository.delete(review);
        return "Review deleted successfully";
    }

    public List<RestaurantReviewDto> orderRestaurantReviewsByRatingDesc(long restaurantId) {
        return restaurantReviewRepository.orderByRatingDescending();
    }

    public List<RestaurantReviewDto> orderRestaurantReviewsByRatingAsc(long restaurantId) {
        return restaurantReviewRepository.orderByRatingAscending();
    }

    public List<RestaurantReviewDto> orderRestaurantReviewsByTimeDesc(long restaurantId) {
        return restaurantReviewRepository.orderByTimeDescending();
    }

    public List<RestaurantReviewDto> orderRestaurantReviewsByTimeAsc(long restaurantId) {
        return restaurantReviewRepository.orderByTimeAscending();
    }
}
