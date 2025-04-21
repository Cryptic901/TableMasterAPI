package com.tablemaster_api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record TimeIntervalDto(LocalDateTime timeStart, LocalDateTime timeEnd) implements Serializable {
}
