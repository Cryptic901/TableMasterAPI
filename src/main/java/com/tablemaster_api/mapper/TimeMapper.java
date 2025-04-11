package com.tablemaster_api.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeMapper {

    public LocalDateTime mapToDateTime(LocalTime localTime) {
        return LocalDateTime.of(LocalDate.now(), localTime);
    }
    public LocalTime mapToTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }
}
