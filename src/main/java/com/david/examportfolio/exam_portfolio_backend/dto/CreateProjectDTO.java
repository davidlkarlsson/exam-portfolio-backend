package com.david.examportfolio.exam_portfolio_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProjectDTO(

        @NotBlank(message = "Title cannot be blank nor contain only whitespaces")
        @Size(min = 5, max = 50)
        String title,
        @NotBlank(message = "Description cannot be blank nor contain only whitespaces")
        @Size(min = 5, max = 200)
        String description,
        String imageUrl,
        String githubUrl
) {}