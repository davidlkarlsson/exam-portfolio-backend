package com.david.examportfolio.exam_portfolio_backend.admin.repository;

import com.david.examportfolio.exam_portfolio_backend.admin.entity.CustomAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomAdminRepository extends JpaRepository<CustomAdmin, UUID> {

    Optional<CustomAdmin> findAdminByEmail(String email);
}
