package com.itesm.infrastructure.mapper;

import com.itesm.domain.models.Comment;
import com.itesm.domain.models.Todo;
import com.itesm.domain.models.TodoWithData;
import com.itesm.infrastructure.persistence.entity.TodoEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public static TodoWithData toFullView(TodoEntity t) {
        List<Comment> comments =
            t.getComments().stream().map(CommentMapper::toDomain).collect(Collectors.toList());

        return new TodoWithData(t.getId(), t.getTitle(), t.getDescription(), t.isCompleted(),
            t.getOwner().getId(), comments, Collections.emptyList());
    }
}
