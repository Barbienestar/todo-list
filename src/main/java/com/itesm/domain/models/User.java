package com.itesm.domain.models;

import java.util.UUID;

public class User {
    private UUID id;
    private String fullName;
    private String email;
    private boolean active;
    private String providerUid;
    private String role;

    public User() {
    }

    public User(
        UUID id, String fullName, String email, boolean active, String providerUid, String role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.active = active;
        this.providerUid = providerUid;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getProviderUid() {
        return providerUid;
    }

    public void setProviderUid(String providerUid) {
        this.providerUid = providerUid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
