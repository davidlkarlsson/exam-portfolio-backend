package com.david.examportfolio.exam_portfolio_backend.admin.service;

import com.david.examportfolio.exam_portfolio_backend.admin.entity.CustomAdmin;
import com.david.examportfolio.exam_portfolio_backend.admin.model.CustomAdminDetails;
import com.david.examportfolio.exam_portfolio_backend.admin.repository.CustomAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

    private final CustomAdminRepository customAdminRepository;

    @Autowired
    public CustomAdminDetailsService(CustomAdminRepository customAdminRepository) {
        this.customAdminRepository = customAdminRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        CustomAdmin customAdmin = customAdminRepository.findAdminByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with email: " + email)
                );
        return new CustomAdminDetails(customAdmin);
    }
}
