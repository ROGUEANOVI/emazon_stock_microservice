package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;

public interface IListCategoriesPersistencePort {

    GenericPagination<Category> listCategories(Integer page, Integer size, String direction);
}
