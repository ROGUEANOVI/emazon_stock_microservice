package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;

import java.util.Optional;

public interface ICategoryPersistencePort {

    void createCategory(Category category);

    GenericPagination<Category> listCategories(Integer page, Integer size, String direction);

    Boolean existsCategoryByName(String name);

    Optional<Category> findCategoryById(Long id);
}
