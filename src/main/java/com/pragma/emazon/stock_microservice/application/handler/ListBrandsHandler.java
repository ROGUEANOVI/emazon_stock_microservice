package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.response.BrandResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.mapper.PaginatedResponseMapper;
import com.pragma.emazon.stock_microservice.domain.port.api.IListBrandsServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ListBrandsHandler implements IListBrandsHandler {
    private final IListBrandsServicePort listBrandsServicePort;
    private final PaginatedResponseMapper paginatedResponseMapper;

    @Override
    public PaginatedResponse<BrandResponse> listBrands(Integer page, Integer size, String direction) {
        return paginatedResponseMapper.toBrandsPaginatedResponse(listBrandsServicePort.listBrands(page, size, direction));
    }
}
