package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.CategoryExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.CategoryBadRequestException;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.port.api.ICreateCategoryServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateCategoryPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsCategoryByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.validation.CategoryValidator;

import java.util.List;
import java.util.Map;

public class CreateCategoryUseCase implements ICreateCategoryServicePort {
    private final ICreateCategoryPersistencePort createCategoryPersistencePort;
    private final IExistsCategoryByNamePersistencePort existsCategoryByNamePersistencePort;

    public CreateCategoryUseCase(ICreateCategoryPersistencePort createCategoryPersistencePort, IExistsCategoryByNamePersistencePort existsCategoryByNamePersistencePort) {
        this.createCategoryPersistencePort = createCategoryPersistencePort;
        this.existsCategoryByNamePersistencePort = existsCategoryByNamePersistencePort;
    }

    @Override
    public void createCategory(Category category) {
        List<Map<String, String>> errors = CategoryValidator.validate(category);

        if (!errors.isEmpty()){
            throw new CategoryBadRequestException(errors);
        }

        if (Boolean.TRUE.equals(existsCategoryByNamePersistencePort.existsCategoryByName(category.getName()))) {
            throw new CategoryAlreadyExistsException(CategoryExceptionMessages.CATEGORY_ALREADY_EXISTS);
        }

        this.createCategoryPersistencePort.createCategory(category);
    }
}
