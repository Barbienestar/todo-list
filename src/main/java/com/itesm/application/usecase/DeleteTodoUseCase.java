package com.itesm.application.usecase;

import com.itesm.domain.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class DeleteTodoUseCase {
    private final TodoRepository todoRepository;

    @Inject
    public DeleteTodoUseCase(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public boolean execute(UUID uuid){
        return todoRepository.deleteTodo(uuid);
    }
}
