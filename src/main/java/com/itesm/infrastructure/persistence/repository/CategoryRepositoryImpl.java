package com.itesm.infrastructure.persistence.repository;

import com.itesm.domain.models.Category;
import com.itesm.domain.repository.CategoryRepository;
import com.itesm.infrastructure.mapper.CategoryMapper;
import com.itesm.infrastructure.persistence.entity.CategoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * CategoryRepositoryImpl
 */
@ApplicationScoped
public class CategoryRepositoryImpl
    implements CategoryRepository, PanacheRepositoryBase<CategoryEntity, UUID> {
    @Inject EntityManager em;
    @Override
    @Transactional
    public Category save(Category category) {
        CategoryEntity entity = CategoryMapper.toEntity(category);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        em.persist(entity);
        return CategoryMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Optional<Category> find(UUID id) {
        return find("id", id).firstResultOptional().map(CategoryMapper::toDomain);
    }

    @Override
    @Transactional
    public List<Category> findAllShallow() {
        return findAll().stream().map(CategoryMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Category> findAllWithTodos() {
        EntityGraph<?> graph = em.getEntityGraph("category.todos");
        TypedQuery<CategoryEntity> query =
            em.createQuery("select c from CategoryEntity c", CategoryEntity.class);
        query.setHint("jakarta.persistence.loadgraph", graph);
        return query.getResultList()
            .stream()
            .map(CategoryMapper::toDomainWithTodos)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findByTitle(String title) {
        return find("title", title).firstResultOptional().map(CategoryMapper::toDomain);
    }
}
