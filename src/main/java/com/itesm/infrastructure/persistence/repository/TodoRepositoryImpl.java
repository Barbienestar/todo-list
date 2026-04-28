package com.itesm.infrastructure.persistence.repository;

import com.itesm.domain.models.Todo;
import com.itesm.domain.models.TodoWithData;
import com.itesm.domain.repository.TodoRepository;
import com.itesm.infrastructure.mapper.TodoMapper;
import com.itesm.infrastructure.persistence.entity.TodoEntity;
import com.itesm.infrastructure.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@ApplicationScoped
public class TodoRepositoryImpl implements TodoRepository, PanacheRepositoryBase<TodoEntity, UUID> {
    @Inject EntityManager em;
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

    @Override
    @Transactional
    public List<TodoWithData> findByOwnerId(UUID ownerId) {
        EntityGraph<?> graph = em.getEntityGraph("todo.full");
        TypedQuery<TodoEntity> query =
            em.createQuery(
                  "select t from TodoEntity t where t.owner.id = :ownerId", TodoEntity.class)
                .setParameter("ownerId", ownerId);
        query.setHint("jakarta.persistence.loadgraph", graph);
        return query.getResultList()
            .stream()
            .map(TodoMapper::toFullView)
            .collect(Collectors.toList());
    }

    @Override
    public List<Todo> listTodos() {
        List<TodoEntity> listEntities = listAll();
        List<Todo> todos = new ArrayList<>();
        for (TodoEntity todoEntity: listEntities) {
            todos.add(TodoMapper.toDomain(todoEntity));
        }
        return todos;
    }

    @Override
    @Transactional
    public boolean deleteTodo(UUID uuid) {
        TodoEntity entity = find("id", uuid).firstResult();
        if (entity == null) {
            return false;
        }
        delete(entity);
        return true;
    }

    @Override
    public Todo findTodoById(UUID uuid){
        TodoEntity entity = find("id", uuid).firstResult();
        return TodoMapper.toDomain(entity);
    }
}
