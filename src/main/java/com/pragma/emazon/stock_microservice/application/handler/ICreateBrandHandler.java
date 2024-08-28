package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateBrandRequest;

public interface ICreateBrandHandler {

    void createBrand(CreateBrandRequest createBrandRequest);
}
