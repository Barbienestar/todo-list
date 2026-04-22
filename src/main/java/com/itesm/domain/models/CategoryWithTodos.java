package com.itesm.domain.models;

import java.util.List;
import java.util.UUID;

/**
 * CategoryWithTodos
 */
public class CategoryWithTodos extends Category {
    private List<TodoWithData> todos;

    public CategoryWithTodos() {}

    public CategoryWithTodos(
        UUID id, String title, String description, String colorHex, List<TodoWithData> todos) {
        super(id, title, description, colorHex);
        this.todos = todos;
    }

    public List<TodoWithData> getTodos() { return todos; }

    public void setTodos(List<TodoWithData> todos) { this.todos = todos; }
}
