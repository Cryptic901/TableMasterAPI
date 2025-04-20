package com.tablemaster_api.dto;

import com.tablemaster_api.entity.Restaurant;
import com.tablemaster_api.entity.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReviewDto(
    User user,
    BigDecimal rating,
    LocalDateTime createdAt)implements Serializable {}
