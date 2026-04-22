package com.itesm.interfaces.rest;

import com.itesm.application.dto.CreateCategoryDto;
import com.itesm.application.usecase.CreateCategoryUseCase;
import com.itesm.domain.models.Category;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 * CategoryResource
 */
@Path("/category")
public class CategoryResource {
    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryResource(CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
    }

    @POST
    public Response createCategory(CreateCategoryDto dto) {
        Category category = createCategoryUseCase.execute(dto);
        return Response.ok(category).build();
    }
}
