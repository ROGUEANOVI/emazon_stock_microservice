package com.pragma.emazon.stock_microservice.domain.port.api;

import com.pragma.emazon.stock_microservice.domain.model.Brand;

public interface ICreateBrandServicePort {

    void createBrand(Brand brand);
}
