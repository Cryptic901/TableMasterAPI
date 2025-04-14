package com.tablemaster_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record VerifyUserDto(
        @Email(message = "Invalid email")
        String email,
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,
        Integer verificationCode) {}
