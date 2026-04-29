package com.itesm.interfaces.rest;

import com.itesm.application.dto.CreateCategoryDto;
import com.itesm.application.usecase.CreateCategoryUseCase;
import com.itesm.application.usecase.ListCategoriesUseCase;
import com.itesm.domain.models.Category;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * CategoryResource
 */
@Path("/category")
public class CategoryResource {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;

    public CategoryResource(CreateCategoryUseCase createCategoryUseCase, ListCategoriesUseCase listCategoriesUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.listCategoriesUseCase = listCategoriesUseCase;
    }

    @POST
    @Path("/create")
    public Response createCategory(CreateCategoryDto dto) {
        Category category = createCategoryUseCase.execute(dto);
        return Response.ok(category).build();
    }

    @GET
        @Path("/listCategories")
    public Response listCategories() {
        List<Category> categoryList = listCategoriesUseCase.execute();
        return Response.ok(categoryList).build();
    }

}
