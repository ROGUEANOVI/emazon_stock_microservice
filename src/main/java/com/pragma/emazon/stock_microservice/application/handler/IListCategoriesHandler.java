package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;

public interface IListCategoriesHandler {

    PaginatedResponse<CategoryResponse> listCategories(Integer page, Integer size, String direction);
}
