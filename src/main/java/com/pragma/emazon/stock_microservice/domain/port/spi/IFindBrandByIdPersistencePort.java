package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Brand;

import java.util.Optional;

public interface IFindBrandByIdPersistencePort {

    Optional<Brand> findBrandById(Long id);
}
