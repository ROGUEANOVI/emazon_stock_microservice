package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateCategoryPersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCategoryJpaAdapter implements ICreateCategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final EntityMapper entityMapper;

    @Override
    public void createCategory(Category category) {

        categoryRepository.save(entityMapper.toCategoryEntity(category));
    }
}
