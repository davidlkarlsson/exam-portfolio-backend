package com.david.examportfolio.exam_portfolio_backend.project.exception;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(Long id) {

        super("No projects found with Id: %d".formatted(id));
    }
}
