package com.david.examportfolio.exam_portfolio_backend.service;

import com.david.examportfolio.exam_portfolio_backend.dto.CreateProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.mapper.ProjectMapper;
import com.david.examportfolio.exam_portfolio_backend.model.Project;
import com.david.examportfolio.exam_portfolio_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(CreateProjectDTO createProjectDTO) {
        return projectRepository.save(projectMapper.toEntity(createProjectDTO));
    }

    public Project updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedProject.getTitle());
                    existing.setDescription(updatedProject.getDescription());
                    existing.setImageUrl(updatedProject.getImageUrl());
                    existing.setGithubUrl(updatedProject.getGithubUrl());
                    return projectRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException ("Project not found"));
    }

    public void deleteProject(Long id) {

        if (!projectRepository.existsById(id)) {
            throw new RuntimeException ("Project not found");
        }

        projectRepository.deleteById(id);
    }


}
