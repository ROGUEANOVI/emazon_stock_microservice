package com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant;


import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class ArticleApiResponses {
    public static final String DESCRIPTION_201 = "Article created";
    public static final String DESCRIPTION_409 = "Article already exists";
    public static final String DESCRIPTION_400 = "Article bad request";

    public static final String SUMMARY_CREATE_ARTICLE = "Create new article";
    public static final String DESCRIPTION_CREATE_ARTICLE = "Rest method for creating article";
}
