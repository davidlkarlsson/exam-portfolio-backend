package com.david.examportfolio.exam_portfolio_backend.mapper;

import com.david.examportfolio.exam_portfolio_backend.dto.CreateProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.dto.ResponseProjectDTO;
import com.david.examportfolio.exam_portfolio_backend.model.Project;
import org.springframework.stereotype.Component;


@Component
public class ProjectMapper {

    public Project toEntity(CreateProjectDTO createProjectDTO) {
        return new Project(
                createProjectDTO.title(),
                createProjectDTO.description(),
                createProjectDTO.imageUrl(),
                createProjectDTO.githubUrl()
        );
    }

    public ResponseProjectDTO toResponseProjectDTO(Project project) {

        return new ResponseProjectDTO(
                project.getTitle(),
                project.getDescription(),
                project.getImageUrl(),
                project.getGithubUrl(),
                project.getCreatedDate(),
                project.getLastModifiedDate()
        );
    }
}
