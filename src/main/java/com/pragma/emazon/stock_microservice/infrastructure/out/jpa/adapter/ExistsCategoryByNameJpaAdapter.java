package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsCategoryByNamePersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistsCategoryByNameJpaAdapter implements IExistsCategoryByNamePersistencePort {
    private final ICategoryRepository categoryRepository;

    @Override
    public boolean existsCategoryByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
