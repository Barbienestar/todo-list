package com.itesm.application.usecase;

import com.itesm.application.dto.CreateCategoryDto;
import com.itesm.domain.models.Category;
import com.itesm.domain.repository.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

/**
 * CreateCategoryUseCase
 */
@ApplicationScoped
public class CreateCategoryUseCase {
    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(CreateCategoryDto dto) {
        Optional<Category> categoryOptional = categoryRepository.findByTitle(dto.getTitle());
        if (categoryOptional.isPresent()) {
            throw new RuntimeException("Category already exists");
        }
        return categoryRepository.save(new Category(
            UUID.randomUUID(), dto.getTitle(), dto.getDescription(), dto.getColorHex()));
    }
}
