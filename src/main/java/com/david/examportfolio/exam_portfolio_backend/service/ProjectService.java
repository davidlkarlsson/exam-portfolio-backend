package com.david.examportfolio.exam_portfolio_backend.service;

import com.david.examportfolio.exam_portfolio_backend.model.Project;
import com.david.examportfolio.exam_portfolio_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }
    public Project createProject(Project project) {
        return projectRepository.save(project);
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
