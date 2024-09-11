package com.pragma.emazon.stock_microservice.infrastructure.constant;


import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class ArticleApiMessages {

    public static final String ROUTE_ARTICLES = "/api/v1/articles";

    public static final String TAG_NAME = "Article";

    public static final String TAG_DESCRIPTION = "Rest controller for article";

    public static final String DESCRIPTION_200 = "Article or articles ok";

    public static final String DESCRIPTION_201 = "Article created";

    public static final String DESCRIPTION_400 = "Article bad request";

    public static final String DESCRIPTION_404 = "Article not found";

    public static final String DESCRIPTION_409 = "Article already exists";

    public static final String SUMMARY_CREATE_ARTICLE = "Create new article";

    public static final String DESCRIPTION_CREATE_ARTICLE = "Rest method for creating article";

    public static final String SUMMARY_LIST_ARTICLES = "List articles";

    public static final String DESCRIPTION_LIST_ARTICLES = "Rest method for list articles";
}
