package com.david.examportfolio.exam_portfolio_backend.admin.controller;


import com.david.examportfolio.exam_portfolio_backend.admin.dto.LoginRequestDTO;
import com.david.examportfolio.exam_portfolio_backend.admin.entity.CustomAdmin;
import com.david.examportfolio.exam_portfolio_backend.admin.model.CustomAdminDetails;
import com.david.examportfolio.exam_portfolio_backend.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
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

        Cookie cookie = new Cookie("authtoken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "token", token,
                "email", customAdmin.getEmail()
        ));
    }
}
