package com.tablemaster_api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {

    @Value("${spring.security.jwt.secret-key}")
    private String jwtSecret;

    @Value("${spring.security.jwt.expiration-time}")
    private Duration jwtExpiration;

    public SecretKey generateKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
    }

    public Duration getExpiration() {
        return jwtExpiration;
    }

    public String buildToken(UserDetails userDetails, Map<String, Object> claims, Duration expirationTime) {
        claims.put("roles", userDetails.getAuthorities());
        return Jwts.builder()
                .claims(claims)
                .signWith(generateKey())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plusSeconds(expirationTime.toMillis())))
                .subject(userDetails.getUsername())
                .compact();
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return buildToken(userDetails, claims, jwtExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", String.class));
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUsername(token))
                && !isTokenExpired(token);
    }

    public Boolean hasRole(String role, String token) {
        return extractRoles(token).contains(role);
    }
}
