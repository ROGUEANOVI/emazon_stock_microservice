package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsBrandByNamePersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistsBrandByNameJpaAdapter implements IExistsBrandByNamePersistencePort {

    private final IBrandRepository brandRepository;

    @Override
    public Boolean existsBrandByName(String name) {

        return brandRepository.existsByName(name);
    }
}
