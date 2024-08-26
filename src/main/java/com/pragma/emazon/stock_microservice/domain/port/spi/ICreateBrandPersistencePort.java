package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Brand;

public interface ICreateBrandPersistencePort {
    void createBrand(Brand brand);
}
