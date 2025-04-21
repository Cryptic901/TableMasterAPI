package com.tablemaster_api.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record RestaurantShortDto(
        String name,
        String location,
        Integer countOfReviews,
        LocalTime workTimeOpen,
        LocalTime workTimeClosed,
        Double rating
) implements Serializable {
}
