package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.ArticleExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundArticleException;
import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.api.IListArticlesServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListArticlesPersistencePort;

import static com.pragma.emazon.stock_microservice.domain.validation.ArticleSort.*;

public class ListArticlesUseCase implements IListArticlesServicePort {

    private final IListArticlesPersistencePort listArticlesPersistencePort;

    public ListArticlesUseCase(IListArticlesPersistencePort listArticlesPersistencePort) {

        this.listArticlesPersistencePort = listArticlesPersistencePort;
    }

    @Override
    public GenericPagination<Article> listArticles(Integer page, Integer size, String direction, String sortBy) {

        GenericPagination<Article> listArticles = listArticlesPersistencePort.listArticles(page, size, direction, resolveSortBy(sortBy));

        if (listArticles.getTotalElements() == 0) {
            throw new NoDataFoundArticleException(ArticleExceptionMessages.NO_DATA_FOUND_ARTICLE);
        }

        return listArticles;
    }
}