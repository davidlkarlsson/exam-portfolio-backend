package com.david.examportfolio.exam_portfolio_backend.jwt;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        log.debug("---- JwtAuthenticationFilter START ----");
        String token = jwtUtils.extractJwtFromCookie(request);

        if (token == null) {

            token = jwtUtils.extractJwtFromRequest(request);
        }

        if (token == null) {
            log.debug("No JWT token found in request");
            filterChain.doFilter(request, response);
            return;
        }
        log.debug("JWT token found in request: {}", token);

        if (jwtUtils.validateJwtToken(token)) {

            String username = jwtUtils.getUsernameFromJwtToken(token);
            Optional<String> roles = jwtUtils.getRolesFromJwtToken(token);

            if (username!= null && roles.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                log.debug("Authorities from database: {}", userDetails.getAuthorities());

                if (userDetails != null && userDetails.isEnabled()) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.debug("Authenticated user from DB: {}",username);
                }

                else  {
                    log.warn("User not found/disabled in DB: {}",username);
                }
            }
            else  {
                log.warn("Invalid JWT token");
            }

            filterChain.doFilter(request, response);
            log.debug("---- JwtAuthenticationFilter END ----");
        }

    }
}
