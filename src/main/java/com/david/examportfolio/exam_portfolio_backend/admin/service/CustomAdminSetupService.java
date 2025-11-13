package com.david.examportfolio.exam_portfolio_backend.admin.service;


import com.david.examportfolio.exam_portfolio_backend.admin.entity.CustomAdmin;
import com.david.examportfolio.exam_portfolio_backend.admin.repository.CustomAdminRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomAdminSetupService {

    private final CustomAdminRepository customAdminRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.admin.email}")
    private String email;
    @Value("${security.admin.username}")
    private String username;
    @Value("${security.admin.password}")
    private String password;

    @Autowired
    public CustomAdminSetupService(CustomAdminRepository customAdminRepository, PasswordEncoder passwordEncoder) {
        this.customAdminRepository = customAdminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Ensure that all @Bean loads in first
    @PostConstruct
    public void createDefaultAdmin() {

        if (customAdminRepository.count() == 0) {

            log.info("Creating default admin...");

            CustomAdmin customAdmin = new CustomAdmin(
                    email,
                    username,
                    passwordEncoder.encode(password)
            );

            customAdminRepository.save(customAdmin);
            log.info("Default admin created successfully");
        }

        log.info("Admin user already exists in database");
    }

}
