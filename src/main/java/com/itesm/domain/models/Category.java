package com.itesm.domain.models;

import java.util.UUID;

/**
 * Category
 */
public class Category {
    private UUID id;
    private String title;
    private String description;
    private String colorHex;

    public Category() {}

    public Category(UUID id, String title, String description, String colorHex) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.colorHex = colorHex;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getColorHex() { return colorHex; }

    public void setColorHex(String colorHex) { this.colorHex = colorHex; }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }
}
