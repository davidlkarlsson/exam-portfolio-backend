package com.david.examportfolio.exam_portfolio_backend.project.repository;

import com.david.examportfolio.exam_portfolio_backend.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrderByCreatedDateDesc();
}
