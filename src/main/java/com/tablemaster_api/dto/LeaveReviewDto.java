package com.tablemaster_api.dto;

import java.math.BigDecimal;

public record LeaveReviewDto(BigDecimal rating, String content) {
}
