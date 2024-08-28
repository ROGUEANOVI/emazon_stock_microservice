package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;

public interface ICreateArticleHandler {

    void createArticle(CreateArticleRequest createArticleRequest);
}
