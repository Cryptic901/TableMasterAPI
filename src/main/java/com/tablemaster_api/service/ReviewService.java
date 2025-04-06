package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.ReviewRepository;
import com.tablemaster_api.abstraction.service.IReviewService;
import com.tablemaster_api.dto.ReviewDto;
import com.tablemaster_api.entity.Review;
import com.tablemaster_api.mapper.ReviewDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {

    private final ReviewRepository restaurantReviewRepository;
    private final ReviewDtoMapper restaurantReviewDtoMapper;


    public ReviewService(ReviewRepository restaurantReviewRepository, ReviewDtoMapper restaurantReviewDtoMapper) {
        this.restaurantReviewRepository = restaurantReviewRepository;
        this.restaurantReviewDtoMapper = restaurantReviewDtoMapper;
    }

    public String leaveReview(Review review) {
        try {
            restaurantReviewRepository.save(review);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "The review was left successfully";
    }

    public List<ReviewDto> getAllReviews(long restaurantId) {
        return restaurantReviewRepository.findReviewByRestaurantId(restaurantId)
                .stream().map(restaurantReviewDtoMapper::fromEntity).toList();
    }

    public Review getReviewById(long restaurantId, long reviewId) {
        return restaurantReviewRepository.findReviewByRestaurantId(restaurantId)
                .stream().filter(r -> r.getId().equals(reviewId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    public ReviewDto updateReview(long reviewId, ReviewDto restaurantReviewDto) {
        Review review = restaurantReviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        restaurantReviewDtoMapper.updateEntityFromDto(restaurantReviewDto, review);
        restaurantReviewRepository.save(review);
        return restaurantReviewDtoMapper.fromEntity(review);
    }

    public String deleteReview(long reviewId) {
        Review review = restaurantReviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        restaurantReviewRepository.delete(review);
        return "Review deleted successfully";
    }

    public List<ReviewDto> orderReviewsByRatingDesc(long restaurantId) {
        return restaurantReviewRepository.orderByRatingDescending();
    }

    public List<ReviewDto> orderReviewsByRatingAsc(long restaurantId) {
        return restaurantReviewRepository.orderByRatingAscending();
    }

    public List<ReviewDto> orderReviewsByTimeDesc(long restaurantId) {
        return restaurantReviewRepository.orderByTimeDescending();
    }

    public List<ReviewDto> orderReviewsByTimeAsc(long restaurantId) {
        return restaurantReviewRepository.orderByTimeAscending();
    }
}
