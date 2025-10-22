package com.david.examportfolio.exam_portfolio_backend.exception;

public record ValidationError(
        String field,
        String message
) {}
