package com.david.examportfolio.exam_portfolio_backend.jwt;


import com.david.examportfolio.exam_portfolio_backend.admin.entity.CustomAdmin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {


    // TODO - Check how to inject environment variable

    private final String base64Secret;
    private final byte[] keyBytes;
    private final SecretKey secretKey;
    private final int jwtExpirationInMs;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public JwtUtils(@Value("${jwt.secret}") String base64Secret) {
        // Validate input before
        if (base64Secret == null || base64Secret.isEmpty()) {
            throw new IllegalArgumentException("JWT secret cannot be null or empty");
        }

        // Initialize all fields
        this.base64Secret = base64Secret;
        this.keyBytes = Base64.getDecoder().decode(base64Secret);
        this.secretKey = Keys.hmacShaKeyFor(this.keyBytes);
        this.jwtExpirationInMs = (int) TimeUnit.HOURS.toMillis(1);

        log.info("JWT utils initialized with secret (last 4 chars): {}",
                base64Secret.substring(Math.max(0, base64Secret.length() - 4)));
    }


    public String generateJwtToken(CustomAdmin customAdmin) {

        log.debug("Generating JWT for user: {} with role: {}",
                customAdmin.getUsername(), customAdmin.getRole());

        String token = Jwts.builder()
                .subject(customAdmin.getEmail())
                .claim("username", customAdmin.getUsername())
                .claim("authorities", customAdmin.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(secretKey)
                .compact();

        log.info("JWT generated successfully for user: {}", customAdmin.getUsername());

        return token;
    }

    public String getEmailFromJwtToken(String token) {

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String email = claims.getSubject();
            log.debug("Extracted email: '{}' from token:", email);
            return email;
        }
        catch (Exception e) {

            log.warn("Failed to extract username from token: {}", e.getMessage());
            return null;
        }
    }

    public String getUsernameFromJwtToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claims.get("username", String.class);
        } catch (Exception e) {
            log.warn("Failed to extract username from token: {}", e.getMessage());
            return null;
        }
    }

    public boolean validateJwtToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseClaimsJws(token);
            log.debug("JWT validation succeeded");
            return true;
        }
        catch (Exception e) {
            log.error("Failed to validate JWT token: {}", e.getMessage());
        }
        return false;
    }

    public Optional<String> getRolesFromJwtToken(String token) {

        try {

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String role = claims.get("authorities", String.class);

        if (role == null || role.isEmpty()) {
            log.warn("No role found in JWT token");
            return Optional.empty();
        }

        log.debug("Extracted roles from JWT token: {}", role);
        return Optional.of(role);
    }
        catch (Exception e) {

        log.warn("Failed to extract roles from token: {}", e.getMessage());
        return Optional.empty();
        }
    }

    public String extractJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if ("authToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
    public String extractJwtFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
