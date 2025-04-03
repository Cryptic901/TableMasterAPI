package com.tablemaster_api.dto;

import java.time.LocalDateTime;

public record RestaurantShortDto(
        String name,
        String location,
        Integer countOfReviews,
        LocalDateTime workTimeOpen,
        LocalDateTime workTimeClosed,
        Double rating
) {}
