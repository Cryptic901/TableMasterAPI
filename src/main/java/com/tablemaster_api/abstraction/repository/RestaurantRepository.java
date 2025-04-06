package com.tablemaster_api.abstraction.repository;

import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r ORDER BY r.rating DESC")
    RestaurantDto orderRestaurantsByRatingDescending();
}
