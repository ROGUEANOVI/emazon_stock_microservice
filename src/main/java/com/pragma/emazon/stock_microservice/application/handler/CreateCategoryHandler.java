package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.application.mapper.CategoryRequestMapper;
import com.pragma.emazon.stock_microservice.domain.port.api.ICreateCategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateCategoryHandler implements ICreateCategoryHandler {
    private final ICreateCategoryServicePort createCategoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;

    @Override
    public void createCategory(CreateCategoryRequest createCategoryRequest) {
        createCategoryServicePort.createCategory(categoryRequestMapper.toCategory(createCategoryRequest));
    }
}
