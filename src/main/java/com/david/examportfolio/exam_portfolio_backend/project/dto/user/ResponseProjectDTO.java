package com.david.examportfolio.exam_portfolio_backend.project.dto.user;

import java.time.LocalDateTime;

public record ResponseProjectDTO(
        Long id,
        String title,
        String description,
        String imageUrl,
        String githubUrl,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {}
