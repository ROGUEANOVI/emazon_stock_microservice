package com.pragma.emazon.stock_microservice.domain.port.spi;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;

import java.util.Optional;

public interface IArticlePersistencePort {

    void createArticle(Article article);

    GenericPagination<Article> listArticles(Integer page, Integer size, String direction, String sortBy);

    Boolean existsArticleByName(String name);

    Optional<Article> findArticleById(Long articleId);

    void updateArticle(Article article);
}
