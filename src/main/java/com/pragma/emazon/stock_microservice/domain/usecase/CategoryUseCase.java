package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.CategoryExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.CategoryBadRequestException;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundCategoryException;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.api.ICategoryServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICategoryPersistencePort;
import com.pragma.emazon.stock_microservice.domain.validation.CategoryValidator;

import java.util.List;
import java.util.Map;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {

        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void createCategory(Category category) {

        List<Map<String, String>> errors = CategoryValidator.validate(category);

        if (!errors.isEmpty()){
            throw new CategoryBadRequestException(errors);
        }

        if (Boolean.TRUE.equals(categoryPersistencePort.existsCategoryByName(category.getName()))) {
            throw new CategoryAlreadyExistsException(CategoryExceptionMessages.CATEGORY_ALREADY_EXISTS, category.getName());
        }

        categoryPersistencePort.createCategory(category);
    }

    @Override
    public GenericPagination<Category> listCategories(Integer page, Integer size, String direction) {

        GenericPagination<Category> listCategories = categoryPersistencePort.listCategories(page, size, direction);

        if (listCategories.getTotalElements() == 0) {
            throw new NoDataFoundCategoryException(CategoryExceptionMessages.NO_DATA_FOUND_CATEGORY);
        }

        return listCategories;
    }
}
