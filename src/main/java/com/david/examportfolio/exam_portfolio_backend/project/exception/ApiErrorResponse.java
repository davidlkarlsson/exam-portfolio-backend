package com.david.examportfolio.exam_portfolio_backend.project.exception;

import java.util.List;

public record ApiErrorResponse(
        int status,
        String error,
        List<ValidationError> errors
) {}
