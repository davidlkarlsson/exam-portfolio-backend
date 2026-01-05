package com.david.examportfolio.exam_portfolio_backend.advice;

public record ValidationError(
        String field,
        String message
) {}
