package com.david.examportfolio.exam_portfolio_backend.project.mapper;

import com.david.examportfolio.exam_portfolio_backend.project.dto.admin.AdminProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.project.dto.admin.RequestProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.project.dto.user.ResponseProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.project.model.Project;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProjectMapper {

    public Project toEntity(RequestProjectDTO requestProjectDTO) {
        return new Project(
                requestProjectDTO.title(),
                requestProjectDTO.description(),
                requestProjectDTO.imageUrl(),
                requestProjectDTO.githubUrl()
        );
    }

    public ResponseProjectDTO toResponseProjectDTO(Project project) {

        return new ResponseProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getImageUrl(),
                project.getGithubUrl(),
                project.getCreatedDate(),
                project.getLastModifiedDate()
        );
    }

    public AdminProjectDTO toAdminProjectDTO(Project project) {

        return new AdminProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getImageUrl(),
                project.getGithubUrl(),
                project.getCreatedDate(),
                project.getLastModifiedDate()
        );
    }

    public List<ResponseProjectDTO> toResponseProjectDTOList(List<Project> projects) {
        return projects.stream()
                .map(this::toResponseProjectDTO)
                .toList();
    }

    public List<AdminProjectDTO> toAdminProjectDTOList(List<Project> projects) {
        return projects.stream()
                .map(this::toAdminProjectDTO)
                .toList();
    }
}
