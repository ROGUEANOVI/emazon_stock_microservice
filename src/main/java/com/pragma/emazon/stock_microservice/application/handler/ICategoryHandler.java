package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;

public interface ICategoryHandler {

    void createCategory(CreateCategoryRequest createCategoryRequest);

    PaginatedResponse<CategoryResponse> listCategories(Integer page, Integer size, String direction);
}
