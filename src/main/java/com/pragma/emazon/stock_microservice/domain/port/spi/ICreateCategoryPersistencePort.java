package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Category;

public interface ICreateCategoryPersistencePort {

    void createCategory(Category category);
}
