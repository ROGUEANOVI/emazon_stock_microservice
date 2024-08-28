package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.CategoryExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundCategoryException;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.api.IListCategoriesServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListCategoriesPersistencePort;

public class ListCategoriesUseCase implements IListCategoriesServicePort {

    private final IListCategoriesPersistencePort listCategoriesPersistencePort;

    public ListCategoriesUseCase(IListCategoriesPersistencePort listCategoriesPersistencePort) {
        this.listCategoriesPersistencePort = listCategoriesPersistencePort;
    }

    @Override
    public GenericPagination<Category> listCategories(Integer page, Integer size, String direction) {

        GenericPagination<Category> listCategories = listCategoriesPersistencePort.listCategories(page, size, direction);

        if (listCategories.getTotalElements() == 0) {
            throw new NoDataFoundCategoryException(CategoryExceptionMessages.NO_DATA_FOUND_CATEGORY);
        }

        return listCategories;
    }
}
