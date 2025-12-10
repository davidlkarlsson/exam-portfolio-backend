package com.david.examportfolio.exam_portfolio_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class AppCorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //Whitelist
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "https://exam-portfolio-frontend.onrender.com"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PATCH", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("Content-Type", "Authorization",
                "X-Requested-With" ));
        corsConfiguration.setAllowCredentials(true); // Cookies enabled
        corsConfiguration.setExposedHeaders(List.of("Authorization", "Set-Cookie"));

        // Backend related endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
