package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.port.spi.IFindBrandByIdPersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FindBrandByIdJpaAdapter implements IFindBrandByIdPersistencePort {

    private final IBrandRepository brandRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<Brand> findBrandById(Long id) {

        return brandRepository.findById(id).map(entityMapper::toBrand);
    }
}
