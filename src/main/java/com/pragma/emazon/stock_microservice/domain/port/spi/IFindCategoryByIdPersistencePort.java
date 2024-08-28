package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Category;

import java.util.Optional;

public interface IFindCategoryByIdPersistencePort {

    Optional<Category> findCategoryById(Long id);
}
