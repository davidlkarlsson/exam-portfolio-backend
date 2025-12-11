package com.david.examportfolio.exam_portfolio_backend.admin.controller;


import com.david.examportfolio.exam_portfolio_backend.admin.dto.LoginRequestDTO;
import com.david.examportfolio.exam_portfolio_backend.admin.entity.CustomAdmin;
import com.david.examportfolio.exam_portfolio_backend.admin.model.CustomAdminDetails;
import com.david.examportfolio.exam_portfolio_backend.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("public/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.email(),
                        loginRequestDTO.password()
                )
        );

        CustomAdminDetails userDetails = (CustomAdminDetails) authentication.getPrincipal();
        CustomAdmin customAdmin = userDetails.getCustomAdmin();

        String token = jwtUtils.generateJwtToken(customAdmin);

        ResponseCookie cookie = ResponseCookie.from("authToken", token)
        .httpOnly(true)
        .secure(true)
        .sameSite("None")
        .path("/")
        .maxAge(3600)
        .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of(
                        "message", "Login successful",
                        "email", customAdmin.getEmail(),
                        "username", customAdmin.getUsername()
                ));
    }

    @GetMapping("admin/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal CustomAdminDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Not authenticated"));
        }

        CustomAdmin customAdmin = userDetails.getCustomAdmin();

        return ResponseEntity.ok(Map.of(
                "username", customAdmin.getUsername(),
                "email", customAdmin.getEmail(),
                "role", customAdmin.getRole()
        ));
    }
}
