package com.itesm.application.usecase;

import com.itesm.domain.models.Todo;
import com.itesm.domain.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ListTodosUseCase {
    private final TodoRepository todoRepository;

    @Inject
    public ListTodosUseCase(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> execute() {
        return todoRepository.listTodos();
    }
}
