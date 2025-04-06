package com.tablemaster_api.dto;

import com.tablemaster_api.entity.Restaurant;
import com.tablemaster_api.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RestaurantReviewDto(
    Restaurant restaurant,
    User user,
    BigDecimal rating,
    LocalDateTime createdAt) {}
