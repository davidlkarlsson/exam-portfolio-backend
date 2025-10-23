package com.david.examportfolio.exam_portfolio_backend.project.exception;

public record ValidationError(
        String field,
        String message
) {}
