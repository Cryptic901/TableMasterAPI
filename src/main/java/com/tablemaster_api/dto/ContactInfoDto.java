package com.tablemaster_api.dto;

import java.io.Serializable;

public record ContactInfoDto(String phone, String email) implements Serializable {
}
