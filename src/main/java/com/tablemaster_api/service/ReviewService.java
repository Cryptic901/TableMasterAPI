package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.RestaurantRepository;
import com.tablemaster_api.abstraction.repository.ReviewRepository;
import com.tablemaster_api.abstraction.repository.UserRepository;
import com.tablemaster_api.abstraction.service.IReviewService;
import com.tablemaster_api.dto.LeaveReviewDto;
import com.tablemaster_api.dto.ReviewDto;
import com.tablemaster_api.entity.Restaurant;
import com.tablemaster_api.entity.Review;
import com.tablemaster_api.entity.User;
import com.tablemaster_api.mapper.ReviewDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService implements IReviewService {

    private final ReviewRepository restaurantReviewRepository;
    private final ReviewDtoMapper restaurantReviewDtoMapper;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final Logger logger = LoggerFactory.getLogger(ReviewService.class);
    private final ReviewRepository reviewRepository;
    private final ReviewDtoMapper reviewDtoMapper;


    public ReviewService(ReviewRepository restaurantReviewRepository, ReviewDtoMapper restaurantReviewDtoMapper,
                         UserRepository userRepository,
                         RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, ReviewDtoMapper reviewDtoMapper) {
        this.restaurantReviewRepository = restaurantReviewRepository;
        this.restaurantReviewDtoMapper = restaurantReviewDtoMapper;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.reviewDtoMapper = reviewDtoMapper;
    }

    public void leaveReview(LeaveReviewDto userReview, long restaurantId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findByUsername(authentication.getName()).
                    orElseThrow(() -> new EntityNotFoundException("User not found"));
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
            Review review = new Review();
            review.setUser(user);
            review.setContent(userReview.content());
            review.setCreatedAt(LocalDateTime.now());
            review.setRating(userReview.rating());
            review.setRestaurant(restaurant);
            restaurantReviewRepository.save(review);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    public List<ReviewDto> getAllReviews(long restaurantId) {
        return restaurantReviewRepository.findReviewByRestaurantId(restaurantId)
                .stream().map(restaurantReviewDtoMapper::fromEntity).toList();
    }

    public Review getReviewById(long reviewId) {
        return restaurantReviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    public void updateReview(long reviewId, LeaveReviewDto reviewDto) {
        Review review = restaurantReviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        restaurantReviewDtoMapper.updateEntityFromDto(reviewDto, review);
        restaurantReviewRepository.save(review);
    }

    public void deleteReview(long reviewId) {
        Review review = restaurantReviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        restaurantReviewRepository.delete(review);
    }

    public List<ReviewDto> getAllSorted(long restaurantId, Sort sort) {
        List<Review> reviews = reviewRepository.findAll(sort);
        return reviews.stream()
                .filter(r -> r.getRestaurant().getId().equals(restaurantId))
                .map(reviewDtoMapper::fromEntity)
                .toList();
    }
}
