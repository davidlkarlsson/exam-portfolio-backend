package com.david.examportfolio.exam_portfolio_backend.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestProjectDTO(

        @NotBlank(message = "Title cannot be blank nor contain only whitespaces")
        @Size(message = "Must contain between 5-50 chars", min = 5, max = 50)
        String title,
        @NotBlank(message = "Description cannot be blank nor contain only whitespaces")
        @Size(message = "Must contain between 5-200 chars", min = 5, max = 200)
        String description,
        String imageUrl,
        String githubUrl
) {}