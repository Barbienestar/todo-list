package com.itesm.domain.models;

import java.util.UUID;

public class Todo {
    private UUID id;
    private String title;
    private String description;
    private boolean completed;
    private UUID userId;

    public Todo() {}

    public Todo(UUID id, String title, String description, boolean completed, UUID userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    public UUID getUserId() { return userId; }

    public void setUserId(UUID userId) { this.userId = userId; }
}
