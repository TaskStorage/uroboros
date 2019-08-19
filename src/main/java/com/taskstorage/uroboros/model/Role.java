package com.taskstorage.uroboros.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, UNKNOWN;

    @Override
    public String getAuthority() {
        return name();
    }
}
