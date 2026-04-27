package com.itesm.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * CategoryEntity
 */
@Entity
@Table(name = "categories")
@NamedEntityGraph(name = "category.todos", attributeNodes = @NamedAttributeNode("todos"))
public class CategoryEntity {
    @Id private UUID id;
    @Column(name = "title") private String title;
    @Column(name = "description") private String description;
    @Column(name = "color_hex") private String colorHex;
    @Column(name = "created_at") private LocalDateTime createdAt;
    @Column(name = "updated_at") private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<TodoEntity> todos = new HashSet<>();

    public CategoryEntity() {}

    public CategoryEntity(UUID id, String title, String description, String colorHex) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.colorHex = colorHex;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getColorHex() { return colorHex; }

    public void setColorHex(String colorHex) { this.colorHex = colorHex; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Set<TodoEntity> getTodos() { return todos; }

    public void setTodos(Set<TodoEntity> todos) { this.todos = todos; }
}
