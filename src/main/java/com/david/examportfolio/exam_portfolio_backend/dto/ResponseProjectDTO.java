package com.david.examportfolio.exam_portfolio_backend.dto;

import java.time.LocalDateTime;

public record ResponseProjectDTO(
        String title,
        String description,
        String imageUrl,
        String githubUrl,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {}
