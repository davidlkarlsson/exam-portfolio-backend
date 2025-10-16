package com.david.examportfolio.exam_portfolio_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String description;
    @Column(length = 200, nullable = false)
    private String imageUrl;
    @Column(length = 100, nullable = false)
    private String githubUrl;
    private LocalDateTime createdDate = LocalDateTime.now();

    public Project() {
    }

    public Project(String title, String description, String imageUrl, String githubUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.githubUrl = githubUrl;
        this.createdDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

}