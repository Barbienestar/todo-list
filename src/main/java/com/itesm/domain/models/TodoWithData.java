package com.itesm.domain.models;

import java.util.List;
import java.util.UUID;

/**
 * TodoWithData
 */
public class TodoWithData extends Todo {
    private List<Comment> comments;
    private List<String> categories;

    public TodoWithData() {
    }

    public TodoWithData(UUID id, String title, String description, boolean completed, UUID userId,
        List<Comment> comments, List<String> categories) {
        super(id, title, description, completed, userId);
        this.comments = comments;
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
