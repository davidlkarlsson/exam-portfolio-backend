package com.david.examportfolio.exam_portfolio_backend.dto.admin;

import java.time.LocalDateTime;

public record AdminProjectDTO(
        Long id,
        String title,
        String description,
        String imageUrl,
        String githubUrl,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {}
