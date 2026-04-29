package com.itesm.application.usecase;

import com.itesm.domain.models.Category;
import com.itesm.domain.models.Todo;
import com.itesm.domain.repository.CategoryRepository;
import com.itesm.domain.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ListCategoriesUseCase {
    private final CategoryRepository categoryRepository;

    @Inject
    public ListCategoriesUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> execute() {
        return categoryRepository.findAllShallow();
    }
}