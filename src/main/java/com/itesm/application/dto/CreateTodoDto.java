package com.itesm.application.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * CreateTodoUseCase
 */
public class CreateTodoDto {
    @NotBlank(message = "Title is required") private String title;
    @NotBlank(message = "Description is required") private String description;
    private List<String> categories;

    public CreateTodoDto() {}

    public CreateTodoDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<String> getCategories() { return categories; }

    public void setCategories(List<String> categories) { this.categories = categories; }
}
