package com.pragma.emazon.stock_microservice.domain.port.api;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;

public interface IListBrandsServicePort {
    GenericPagination<Brand> listBrands(Integer page, Integer size, String direction);
}
