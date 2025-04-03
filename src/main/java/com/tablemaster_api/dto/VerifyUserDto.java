package com.tablemaster_api.dto;

public record VerifyUserDto(
        String email,
        String password,
        String verificationCode) {}
