package com.pragma.emazon.stock_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ArticleTag {

    public static final String TAG_NAME = "Article";
    public static final String TAG_DESCRIPTION = "Rest controller for article";
    public static final String PATH = "/articles/";
}
