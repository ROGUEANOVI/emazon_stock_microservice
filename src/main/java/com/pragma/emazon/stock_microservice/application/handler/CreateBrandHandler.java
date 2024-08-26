package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateBrandRequest;
import com.pragma.emazon.stock_microservice.application.mapper.BrandRequestMapper;
import com.pragma.emazon.stock_microservice.domain.port.api.ICreateBrandServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateBrandHandler implements ICreateBrandHandler {
    private final ICreateBrandServicePort createBrandServicePort;
    private final BrandRequestMapper brandRequestMapper;

    @Override
    public void createBrand(CreateBrandRequest createBrandRequest) {
       createBrandServicePort.createBrand(brandRequestMapper.toBrand(createBrandRequest));
    }
}
