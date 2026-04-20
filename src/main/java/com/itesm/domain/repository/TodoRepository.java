package com.itesm.domain.repository;

import com.itesm.domain.models.Todo;
import java.util.Optional;
import java.util.UUID;

/**
 * TodoRepository
 */
public interface TodoRepository {
    public Todo save(Todo todo);
    public void delete(UUID id);
    public Optional<Todo> find(UUID id);
}
