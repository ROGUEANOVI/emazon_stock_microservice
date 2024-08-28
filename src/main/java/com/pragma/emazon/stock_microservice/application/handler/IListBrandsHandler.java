package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.response.BrandResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;

public interface IListBrandsHandler {

    PaginatedResponse<BrandResponse> listBrands(Integer page, Integer size, String direction);
}
