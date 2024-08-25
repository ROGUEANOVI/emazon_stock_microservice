package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.mapper.PaginatedResponseMapper;
import com.pragma.emazon.stock_microservice.domain.port.api.IListCategoriesServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ListCategoriesHandler implements IListCategoriesHandler {
    private final IListCategoriesServicePort listCategoriesServicePort;
    private final PaginatedResponseMapper paginatedResponseMapper;

    @Override
    public PaginatedResponse<CategoryResponse> listCategories(Integer page, Integer size, String direction) {
        return paginatedResponseMapper.toCategoriesPaginatedResponse(listCategoriesServicePort.listCategories(page, size, direction));
    }
}
