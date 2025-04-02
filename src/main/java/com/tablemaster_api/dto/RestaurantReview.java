package com.tablemaster_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RestaurantReview(
    Long id,
    Long restaurantId,
    Long userId,
    BigDecimal rating,
    String content,
    LocalDateTime createdAt
) {}
