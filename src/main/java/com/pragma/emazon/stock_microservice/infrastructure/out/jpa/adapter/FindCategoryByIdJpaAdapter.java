package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.port.spi.IFindCategoryByIdPersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FindCategoryByIdJpaAdapter implements IFindCategoryByIdPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<Category> findCategoryById(Long id) {

        return categoryRepository.findById(id).map(entityMapper::toCategory);
    }
}
