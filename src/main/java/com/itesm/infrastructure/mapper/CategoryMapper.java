package com.itesm.infrastructure.mapper;

import com.itesm.domain.models.Category;
import com.itesm.domain.models.CategoryWithTodos;
import com.itesm.domain.models.TodoWithData;
import com.itesm.infrastructure.persistence.entity.CategoryEntity;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CategoryMapper
 */
public class CategoryMapper {
    public static CategoryEntity toEntity(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setTitle(category.getTitle());
        entity.setDescription(category.getDescription());
        entity.setColorHex(category.getColorHex());
        return entity;
    }

    public static Category toDomain(CategoryEntity entity) {
        Category category = new Category();
        category.setId(entity.getId());
        category.setTitle(entity.getTitle());
        category.setDescription(entity.getDescription());
        category.setColorHex(entity.getColorHex());
        return category;
    }

    public static CategoryWithTodos toDomainWithTodos(CategoryEntity entity) {
        CategoryWithTodos category = new CategoryWithTodos();
        category.setId(entity.getId());
        category.setTitle(entity.getTitle());
        category.setDescription(entity.getDescription());
        category.setColorHex(entity.getColorHex());

        if (entity.getTodos() != null) {
            List<TodoWithData> todos =
                entity.getTodos().stream().map(TodoMapper::toFullView).collect(Collectors.toList());
            category.setTodos(todos);
        }
        return category;
    }
}
