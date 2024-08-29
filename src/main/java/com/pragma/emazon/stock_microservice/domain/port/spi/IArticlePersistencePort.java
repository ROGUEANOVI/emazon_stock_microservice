package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;

public interface IArticlePersistencePort {

    void createArticle(Article article);

    GenericPagination<Article> listArticles(Integer page, Integer size, String direction, String sortBy);

    Boolean existsArticleByName(String name);
}
