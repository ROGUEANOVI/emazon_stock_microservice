package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;

import java.util.Optional;

public interface IBrandPersistencePort {

    void createBrand(Brand brand);

    GenericPagination<Brand> listBrands(Integer page, Integer size, String direction);

    Boolean existsBrandByName(String name);

    Optional<Brand> findBrandById(Long id);

}
