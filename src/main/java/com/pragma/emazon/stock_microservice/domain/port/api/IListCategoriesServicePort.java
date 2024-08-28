package com.pragma.emazon.stock_microservice.domain.port.api;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;

public interface IListCategoriesServicePort {

    GenericPagination<Category> listCategories(Integer page, Integer size, String direction);
}
