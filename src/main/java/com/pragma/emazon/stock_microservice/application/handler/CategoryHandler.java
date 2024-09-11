package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.mapper.CategoryRequestMapper;
import com.pragma.emazon.stock_microservice.application.mapper.PaginatedResponseMapper;
import com.pragma.emazon.stock_microservice.domain.port.api.ICategoryServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final PaginatedResponseMapper paginatedResponseMapper;

    @Override
    public void createCategory(@Valid CreateCategoryRequest createCategoryRequest) {

        categoryServicePort.createCategory(categoryRequestMapper.toCategory(createCategoryRequest));
    }

    @Override
    public PaginatedResponse<CategoryResponse> listCategories(Integer page, Integer size, String direction) {

        return paginatedResponseMapper.toCategoriesPaginatedResponse(categoryServicePort.listCategories(page, size, direction));
    }
}
