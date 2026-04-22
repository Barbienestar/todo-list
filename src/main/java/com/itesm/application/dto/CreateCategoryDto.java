package com.itesm.application.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * CreateCategoryDto
 */
public class CreateCategoryDto {
    @NotBlank(message = "Title is required") private String title;
    private String description;
    private String colorHex;

    public CreateCategoryDto() {}

    public CreateCategoryDto(String title, String description, String colorHex) {
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
}
