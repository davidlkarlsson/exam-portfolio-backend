package com.david.examportfolio.exam_portfolio_backend.project.repository;

import com.david.examportfolio.exam_portfolio_backend.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
