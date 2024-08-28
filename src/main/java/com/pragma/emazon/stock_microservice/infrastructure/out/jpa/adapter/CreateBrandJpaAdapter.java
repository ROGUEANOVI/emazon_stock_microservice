package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateBrandPersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateBrandJpaAdapter implements ICreateBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final EntityMapper entityMapper;
    @Override
    public void createBrand(Brand brand) {

        brandRepository.save(entityMapper.toBrandEntity(brand));
    }
}
