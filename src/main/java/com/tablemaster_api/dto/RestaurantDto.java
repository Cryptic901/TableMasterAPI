package com.tablemaster_api.dto;

import com.tablemaster_api.entity.Reservation;
import com.tablemaster_api.entity.Tag;
import com.tablemaster_api.enums.DaysOfWeek;

import java.time.LocalDateTime;
import java.util.List;

public record RestaurantDto(
        String name,
        String description,
        String address,
        String location,
        Double rating,
        Integer countOfReviews,
        LocalDateTime workTimeOpen,
        LocalDateTime workTimeClosed,
        List<DaysOfWeek> workDays,
        Set<Tag> tags,
        List<Reservation> reservations
) {}
