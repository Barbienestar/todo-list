package com.itesm.interfaces.rest;

import com.itesm.application.dto.CreateTodoDto;
import com.itesm.application.usecase.*;
import com.itesm.domain.models.Todo;
import com.itesm.domain.models.TodoWithData;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;


@Path("/todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private final CreateTodoUseCase createTodoUseCase;
    private final GetUserTodosUseCase getUserTodosUseCase;
    private final ListTodosUseCase listTodosUseCase;
    private final FindByIdUseCase findByIdUseCase;
    private final DeleteTodoUseCase deleteTodoUseCase;

    @Inject
    public TodoResource(
        CreateTodoUseCase createTodoUseCase, GetUserTodosUseCase getUserTodosUseCase, ListTodosUseCase listTodosUseCase, DeleteTodoUseCase deleteTodoUseCase, FindByIdUseCase findByIdUseCase) {
        this.createTodoUseCase = createTodoUseCase;
        this.getUserTodosUseCase = getUserTodosUseCase;
        this.listTodosUseCase = listTodosUseCase;
        this.deleteTodoUseCase = deleteTodoUseCase;
        this.findByIdUseCase = findByIdUseCase;
    }

    @POST
    @Path("/create")
    public Response createTodo(CreateTodoDto createTodoDto) {
        Todo todo = createTodoUseCase.execute(createTodoDto);
        return Response.ok(todo).build();
    }

    @GET
    @Path("/getUser")
    public Response getUserTodos() {
        List<TodoWithData> todos = getUserTodosUseCase.execute();
        return Response.ok(todos).build();
    }

    @GET
    @Path("/listTodos")
    public Response listTodos() {
        List<Todo> listTodo = listTodosUseCase.execute();
        return Response.ok(listTodo).build();
    }

    @DELETE
    @Path("/deleteTodo/{uuid}")
    public Response deleteTodo(@PathParam("uuid") UUID uuid) {
        Boolean status = deleteTodoUseCase.execute(uuid);
        return Response.ok(status).build();
    }

    @GET
    @Path("/findTodoById/{uuid}")
    public Response findTodoById(@PathParam("uuid") UUID uuid) {
        Todo todo = findByIdUseCase.execute(uuid);
        return Response.ok(todo).build();
    }
}
