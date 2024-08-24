package com.pragma.emazon.stock_microservice.domain.port.api;

import com.pragma.emazon.stock_microservice.domain.model.Category;

public interface ICreateCategoryServicePort {
    void createCategory(Category category);
}
