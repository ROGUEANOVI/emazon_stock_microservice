package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;

public interface ICreateCategoryHandler {
    void createCategory(CreateCategoryRequest createCategoryRequest);
}
