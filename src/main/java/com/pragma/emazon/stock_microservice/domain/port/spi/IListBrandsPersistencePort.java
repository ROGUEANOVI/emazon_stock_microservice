package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;

public interface IListBrandsPersistencePort {
    GenericPagination<Brand> listBrand(Integer page, Integer size, String direction);
}
