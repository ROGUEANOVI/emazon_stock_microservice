package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import jakarta.validation.Valid;

public interface ICategoryHandler {

    void createCategory(@Valid CreateCategoryRequest createCategoryRequest);

    PaginatedResponse<CategoryResponse> listCategories(Integer page, Integer size, String direction);
}
