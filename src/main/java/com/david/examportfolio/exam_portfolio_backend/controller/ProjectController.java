package com.david.examportfolio.exam_portfolio_backend.controller;

import com.david.examportfolio.exam_portfolio_backend.model.Project;
import com.david.examportfolio.exam_portfolio_backend.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {

        List<Project> projects = projectService.getAllProjects();

        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {

        Optional<Project> project = projectService.getProjectById(id);

        if (project.isPresent()) {
            return ResponseEntity.ok(project.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {

        Project createdProject = projectService.createProject(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {

        try {
            Project updatedProject = projectService.updateProject(id, project);
            return ResponseEntity.ok(updatedProject);
            }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {

        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
            }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }



}
