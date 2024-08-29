package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateBrandRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.BrandResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;

public interface IBrandHandler {

    void createBrand(CreateBrandRequest createBrandRequest);

    PaginatedResponse<BrandResponse> listBrands(Integer page, Integer size, String direction);
}
