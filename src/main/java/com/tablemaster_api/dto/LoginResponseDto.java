package com.tablemaster_api.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public record LoginResponseDto(String token, Duration expires) {
}
