package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Article;

public interface ICreateArticlePersistencePort {
    void createArticle(Article article);
}
