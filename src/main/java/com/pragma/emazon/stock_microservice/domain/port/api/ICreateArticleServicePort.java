package com.pragma.emazon.stock_microservice.domain.port.api;

import com.pragma.emazon.stock_microservice.domain.model.Article;

public interface ICreateArticleServicePort {
    void createArticle(Article article);
}
