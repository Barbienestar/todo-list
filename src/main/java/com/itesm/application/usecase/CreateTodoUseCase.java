package com.itesm.application.usecase;

import com.itesm.application.dto.CreateTodoDto;
import com.itesm.application.security.AuthenticatedUserContext;
import com.itesm.domain.models.Todo;
import com.itesm.domain.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * CreateTodoUseCase
 */
@ApplicationScoped
public class CreateTodoUseCase {
    private final TodoRepository todoRepository;
    private final AuthenticatedUserContext aUserContext;

    @Inject
    public CreateTodoUseCase(TodoRepository todoRepository, AuthenticatedUserContext aUserContext) {
        this.todoRepository = todoRepository;
        this.aUserContext = aUserContext;
    }

    public Todo execute(CreateTodoDto createTodoDto) {
        Todo todo = new Todo();
        todo.setId(UUID.randomUUID());
        todo.setTitle(createTodoDto.getTitle());
        todo.setDescription(createTodoDto.getDescription());
        todo.setUserId(aUserContext.getCurrentUser().getUserId());
        List<String> categories = createTodoDto.getCategories();
        List<UUID> categoryIds = new ArrayList<>();
        if (categories == null) {
            categories = new ArrayList<>();
        }
        for (String category : categories) {
            categoryIds.add(UUID.fromString(category));
        }
        todo.setCompleted(false);
        return todoRepository.save(todo, categoryIds);
    }
}
