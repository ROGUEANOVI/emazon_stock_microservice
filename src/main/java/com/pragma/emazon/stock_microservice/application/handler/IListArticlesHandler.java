package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;

public interface IListArticlesHandler {

    PaginatedResponse<ArticleResponse> listArticles(Integer page, Integer size, String direction, String sortBy);
}
