package com.tablemaster_api.abstraction.repository;

import com.tablemaster_api.dto.ReviewDto;
import com.tablemaster_api.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId")
    List<Review> findReviewByRestaurantId(@Param("restaurantId") long restaurantId);

    @Query("SELECT r FROM Review r ORDER BY r.rating DESC")
    List<ReviewDto> orderByRatingDescending();

    @Query("SELECT r FROM Review r ORDER BY r.rating ASC")
    List<ReviewDto> orderByRatingAscending();

    @Query("SELECT r FROM Review r ORDER BY r.createdAt DESC")
    List<ReviewDto> orderByTimeDescending();

    @Query("SELECT r FROM Review r ORDER BY r.createdAt ASC")
    List<ReviewDto> orderByTimeAscending();
}
