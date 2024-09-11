package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import jakarta.validation.Valid;

public interface IArticleHandler {

    void createArticle(@Valid CreateArticleRequest createArticleRequest);

    PaginatedResponse<ArticleResponse> listArticles(Integer page, Integer size, String direction, String sortBy);
}
