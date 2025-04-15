package com.tablemaster_api.dto;

import jakarta.validation.constraints.Email;

public record VerifyUserDto(
        @Email(message = "Invalid email")
        String email,
        Integer verificationCode) {}
