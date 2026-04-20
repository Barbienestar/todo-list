package com.itesm.interfaces.rest;

import com.itesm.application.dto.CreateTodoDto;
import com.itesm.application.usecase.CreateTodoUseCase;
import com.itesm.application.usecase.GetUserTodosUseCase;
import com.itesm.domain.models.Todo;
import com.itesm.domain.models.TodoWithData;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * TodoResource
 */
@Path("/todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private final CreateTodoUseCase createTodoUseCase;
    private final GetUserTodosUseCase getUserTodosUseCase;

    @Inject
    public TodoResource(
        CreateTodoUseCase createTodoUseCase, GetUserTodosUseCase getUserTodosUseCase) {
        this.createTodoUseCase = createTodoUseCase;
        this.getUserTodosUseCase = getUserTodosUseCase;
    }

    @POST
    public Response createTodo(CreateTodoDto createTodoDto) {
        Todo todo = createTodoUseCase.execute(createTodoDto);
        return Response.ok(todo).build();
    }

    @GET
    public Response getUserTodos() {
        List<TodoWithData> todos = getUserTodosUseCase.execute();
        return Response.ok(todos).build();
    }
}
