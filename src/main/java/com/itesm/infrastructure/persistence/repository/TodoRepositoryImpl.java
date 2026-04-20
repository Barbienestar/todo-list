package com.itesm.infrastructure.persistence.repository;

import com.itesm.domain.models.Todo;
import com.itesm.domain.repository.TodoRepository;
import com.itesm.infrastructure.mapper.TodoMapper;
import com.itesm.infrastructure.persistence.entity.TodoEntity;
import com.itesm.infrastructure.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * TodoRepositoryImpl
 */
public class TodoRepositoryImpl implements TodoRepository, PanacheRepositoryBase<TodoEntity, UUID> {
    @Override
    @Transactional
    public Todo save(Todo todo) {
        TodoEntity entity = TodoMapper.toEntity(todo);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        if (todo.getUserId() != null) {
            entity.setOwner(getEntityManager().getReference(UserEntity.class, todo.getUserId()));
        }
        persist(entity);
        return TodoMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Todo> find(UUID id) {
        return find("id", id).firstResultOptional().map(TodoMapper::toDomain);
    }
}
