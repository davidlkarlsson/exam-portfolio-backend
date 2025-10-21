package com.david.examportfolio.exam_portfolio_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "github_url")
    private String githubUrl;

    // TODO - Check out formatting of JSON, what's wrong?
    @CreatedDate
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    public Project() {
    }

    public Project(String title, String description, String imageUrl, String githubUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.githubUrl = githubUrl;
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

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
}