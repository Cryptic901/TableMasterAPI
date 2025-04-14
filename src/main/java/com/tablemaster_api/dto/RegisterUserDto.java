package com.tablemaster_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tablemaster_api.enums.Roles;

import java.util.Collection;

public record RegisterUserDto(
        String username,
        String email,
        String password,
        @JsonIgnore Collection<Roles> roles) {}
