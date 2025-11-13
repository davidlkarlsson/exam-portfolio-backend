package com.david.examportfolio.exam_portfolio_backend.admin.model;


import com.david.examportfolio.exam_portfolio_backend.admin.entity.CustomAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomAdminDetails implements UserDetails {

    private final CustomAdmin customAdmin;

    public CustomAdminDetails(CustomAdmin customAdmin) {
        this.customAdmin = customAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(customAdmin.getRole()));
    }

    @Override
    public String getPassword() {
        return customAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return customAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return customAdmin.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return customAdmin.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return customAdmin.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return customAdmin.isEnabled();
    }

    public CustomAdmin getCustomAdmin() {
        return customAdmin;
    }
}
