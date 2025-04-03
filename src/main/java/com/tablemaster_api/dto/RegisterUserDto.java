package com.tablemaster_api.dto;

public record RegisterUserDto(
        String username,
        String email,
        String password) {}
