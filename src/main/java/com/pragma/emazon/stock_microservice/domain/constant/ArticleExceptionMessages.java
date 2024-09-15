package com.pragma.emazon.stock_microservice.domain.constant;

public class ArticleExceptionMessages {

    public static final String ARTICLE_ALREADY_EXISTS = "Article with name '%s' already exists";

    public static final String NO_DATA_FOUND_ARTICLE = "No data found article for the requested petition";

    public static final String ARTICLE_NOT_FOUND = "Article with id '%s' not found";

    private ArticleExceptionMessages() {}
}
