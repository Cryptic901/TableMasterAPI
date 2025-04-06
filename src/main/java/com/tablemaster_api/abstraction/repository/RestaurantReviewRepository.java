package com.tablemaster_api.abstraction.repository;

import com.tablemaster_api.dto.RestaurantReviewDto;
import com.tablemaster_api.entity.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long> {

    @Query("SELECT r FROM RestaurantReview r WHERE r.restaurant.id = :restaurantId")
    List<RestaurantReview> findRestaurantReviewByRestaurantId(@Param("restaurantId") long restaurantId);

    @Query("SELECT r FROM RestaurantReview r ORDER BY r.rating DESC")
    List<RestaurantReviewDto> orderByRatingDescending();

    @Query("SELECT r FROM RestaurantReview r ORDER BY r.rating ASC")
    List<RestaurantReviewDto> orderByRatingAscending();

    @Query("SELECT r FROM RestaurantReview r ORDER BY r.createdAt DESC")
    List<RestaurantReviewDto> orderByTimeDescending();

    @Query("SELECT r FROM RestaurantReview r ORDER BY r.createdAt ASC")
    List<RestaurantReviewDto> orderByTimeAscending();
}
