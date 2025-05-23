package com.tablemaster_api.dto;

import com.tablemaster_api.entity.Reservation;
import com.tablemaster_api.entity.Tag;
import com.tablemaster_api.enums.DaysOfWeek;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public record RestaurantDto(
        String name,
        String description,
        String address,
        String location,
        Double rating,
        Integer countOfReviews,
        LocalTime workTimeOpen,
        LocalTime workTimeClosed,
        List<DaysOfWeek> workDays,
        Set<Tag> tags,
        List<Reservation> reservations
) implements Serializable {
}
