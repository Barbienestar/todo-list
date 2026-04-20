package com.itesm.infrastructure.mapper;

import com.itesm.domain.models.Todo;
import com.itesm.infrastructure.persistence.entity.TodoEntity;

/**
 * TodoMapper
 */
public class TodoMapper {
    public static Todo toDomain(TodoEntity entity) {
        Todo todo = new Todo();
        todo.setId(entity.getId());
        todo.setTitle(entity.getTitle());
        todo.setDescription(entity.getDescription());
        todo.setCompleted(entity.isCompleted());
        if (entity.getOwner() != null) {
            todo.setUserId(entity.getOwner().getId());
        }
        return todo;
    }

    public static TodoEntity toEntity(Todo todo) {
        TodoEntity entity = new TodoEntity();
        entity.setId(todo.getId());
        entity.setTitle(todo.getTitle());
        entity.setDescription(todo.getDescription());
        entity.setCompleted(todo.isCompleted());
        return entity;
    }
}
