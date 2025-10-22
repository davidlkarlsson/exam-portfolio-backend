package com.david.examportfolio.exam_portfolio_backend.service;

import com.david.examportfolio.exam_portfolio_backend.dto.admin.AdminProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.dto.admin.RequestProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.dto.user.ResponseProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.exception.ProjectNotFoundException;
import com.david.examportfolio.exam_portfolio_backend.mapper.ProjectMapper;
import com.david.examportfolio.exam_portfolio_backend.model.Project;
import com.david.examportfolio.exam_portfolio_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ResponseProjectDTO> getAllProjects() {

        List<Project> projects = projectRepository.findAll();

        return projectMapper.toResponseProjectDTOList(projects);
    }

    public List<AdminProjectDTO> getAllAdminProjects() {

        List<Project> projects = projectRepository.findAll();

        return projectMapper.toAdminProjectDTOList(projects);
    }

    public AdminProjectDTO getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        return projectMapper.toAdminProjectDTO(project);

    }

    public ResponseProjectDTO createProject(RequestProjectDTO createdProject) {

        Project savedProject = projectRepository.save(projectMapper.toEntity(createdProject));

        return projectMapper.toResponseProjectDTO(savedProject);
    }

    /**
     * Updates an existing project with new data provided in the {@link RequestProjectDTO}.
     *
     * This method looks up a project by its ID, applies the updated values from the DTO,
     * and saves the modified entity back to the database. If the project with the specified
     * ID does not exist, a {@link ProjectNotFoundException} is thrown.
     *
     *
     * @param id               the ID of the project to update
     * @param updatedProject   the DTO containing the updated project data
     * @return {@link ResponseProjectDTO} representing the updated project
     * @throws ProjectNotFoundException if no project with the given ID exists
     */


    public ResponseProjectDTO updateProject(Long id, RequestProjectDTO updatedProject) {

        return projectRepository.findById(id)
                .map(existing -> {
                    Project updated = projectMapper.toEntity(updatedProject);

                    existing.setTitle(updated.getTitle());
                    existing.setDescription(updated.getDescription());
                    existing.setImageUrl(updated.getImageUrl());
                    existing.setGithubUrl(updated.getGithubUrl());

                    Project savedProject = projectRepository.save(existing);
                    return projectMapper.toResponseProjectDTO(savedProject);
                })
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public void deleteProject(Long id) {

        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(id);
        }

        projectRepository.deleteById(id);
    }


}
