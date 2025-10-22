package com.david.examportfolio.exam_portfolio_backend.repository;

import com.david.examportfolio.exam_portfolio_backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
