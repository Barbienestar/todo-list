package com.itesm.application.usecase;

import com.itesm.domain.models.Todo;
import com.itesm.domain.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class FindByIdUseCase {
    private final TodoRepository todoRepository;

    @Inject
    public FindByIdUseCase(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo execute(UUID uuid){
        return todoRepository.findTodoById(uuid);
    }
}
