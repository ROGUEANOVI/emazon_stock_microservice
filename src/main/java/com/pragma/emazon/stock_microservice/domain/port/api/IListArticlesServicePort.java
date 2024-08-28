package com.pragma.emazon.stock_microservice.domain.port.api;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;

public interface IListArticlesServicePort {

    GenericPagination<Article> listArticles(Integer page, Integer size, String direction, String sortBy);
}
