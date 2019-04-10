package com.financialhouse.reporting.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    API_USER;

    public String getAuthority() {
        return name();
    }
}