package com.itesm.application.usecase;

import com.itesm.application.security.AuthenticatedUserContext;
import com.itesm.domain.models.TodoWithData;
import com.itesm.domain.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

/**
 * GetUserTodosUseCase
 */
@ApplicationScoped
public class GetUserTodosUseCase {
    private final TodoRepository todoRepository;
    private final AuthenticatedUserContext aUserContext;

    @Inject
    public GetUserTodosUseCase(
        TodoRepository todoRepository, AuthenticatedUserContext aUserContext) {
        this.todoRepository = todoRepository;
        this.aUserContext = aUserContext;
    }

    public List<TodoWithData> execute() {
        return todoRepository.findByOwnerId(aUserContext.getCurrentUser().getUserId());
    }
}
