package com.david.examportfolio.exam_portfolio_backend.controller;

import com.david.examportfolio.exam_portfolio_backend.dto.admin.AdminProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.dto.admin.RequestProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.dto.user.ResponseProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/public/projects")
    public ResponseEntity<List<ResponseProjectDTO>> getAllProjects() {

        List<ResponseProjectDTO> projects = projectService.getAllProjects();

        return ResponseEntity.ok(projects);
    }

    @GetMapping("/admin/projects/getall")
    public ResponseEntity<List<AdminProjectDTO>> getAllAdminProjects() {

        List<AdminProjectDTO> projects = projectService.getAllAdminProjects();

        return ResponseEntity.ok(projects);
    }

    @GetMapping("/admin/projects/{id}")
    public ResponseEntity<AdminProjectDTO> getProjectById(@PathVariable Long id) {

        AdminProjectDTO project = projectService.getProjectById(id);

        return ResponseEntity.ok().body(project);
    }

    @PostMapping("/admin/projects/create")
    public ResponseEntity<ResponseProjectDTO> createProject(@RequestBody RequestProjectDTO requestProjectDTO) {

        ResponseProjectDTO project = projectService.createProject(requestProjectDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @PatchMapping("/admin/projects/update/{id}")
    public ResponseEntity<ResponseProjectDTO> updateProject(@PathVariable Long id, @RequestBody RequestProjectDTO project) {

            ResponseProjectDTO updatedProject = projectService.updateProject(id, project);

            return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/admin/projects/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {

            projectService.deleteProject(id);

            return ResponseEntity.noContent().build();
    }
}
