package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateBrandRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.BrandResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.pragma.emazon.stock_microservice.application.mapper.PaginatedResponseMapper;
import com.pragma.emazon.stock_microservice.domain.port.api.IBrandServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final PaginatedResponseMapper paginatedResponseMapper;

    @Override
    public void createBrand(CreateBrandRequest createBrandRequest) {

        brandServicePort.createBrand(brandRequestMapper.toBrand(createBrandRequest));
    }

    @Override
    public PaginatedResponse<BrandResponse> listBrands(Integer page, Integer size, String direction) {

        return paginatedResponseMapper.toBrandsPaginatedResponse(brandServicePort.listBrands(page, size, direction));
    }
}
