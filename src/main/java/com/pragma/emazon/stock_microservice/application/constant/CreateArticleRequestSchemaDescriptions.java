package com.pragma.emazon.stock_microservice.application.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateArticleRequestSchemaDescriptions {

    public static final String CREATE_ARTICLE_REQUEST_DESCRIPTION = "Request body to create an article";

    public static final String ARTICLE_NAME_DESCRIPTION = "Name of the article to be created";

    public static final String ARTICLE_NAME_EXAMPLE = "Smartphone";

    public static final String ARTICLE_DESCRIPTION_DESCRIPTION = "Description of the article";

    public static final String ARTICLE_DESCRIPTION_EXAMPLE = "Latest model with advanced features";

    public static final String ARTICLE_QUANTITY_DESCRIPTION = "Quantity of the article available in stock";

    public static final String ARTICLE_QUANTITY_EXAMPLE = "50";

    public static final String ARTICLE_PRICE_DESCRIPTION = "Price of the article";

    public static final String ARTICLE_PRICE_EXAMPLE = "699.99";

    public static final String ARTICLE_BRAND_ID_DESCRIPTION = "ID of the brand associated with the article";

    public static final String ARTICLE_BRAND_ID_EXAMPLE = "1";

    public static final String ARTICLE_CATEGORIES_DESCRIPTION = "List of category IDs associated with the article";
    public static final String ARTICLE_CATEGORIES_EXAMPLE = "[1, 2, 3]";
}
