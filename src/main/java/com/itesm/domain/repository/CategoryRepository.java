package com.itesm.domain.repository;

import com.itesm.domain.models.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * CategoryRepository
 */
public interface CategoryRepository {
    public Category save(Category category);
    public Optional<Category> find(UUID id);
    public Optional<Category> findByTitle(String title);
    public List<Category> findAllShallow();
    public List<Category> findAllWithTodos();
}
