package com.tablemaster_api.service;

import org.springframework.beans.factory.annotation.Value;

public class JwtService {

    @Value("${spring.security.jwt.secret-key}")
    private String jwtSecret;

    @Value("${spring.security.jwt.expiration-time}")
    private long jwtExpiration;
}
