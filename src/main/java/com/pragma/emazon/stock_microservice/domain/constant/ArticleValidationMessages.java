package com.pragma.emazon.stock_microservice.domain.constant;

import java.math.BigDecimal;

public class ArticleValidationMessages {

    public static final String TABLE_NAME = "article";
    public static final String TABLE_NAME_MANY_TO_MANY = "article_category";
    public static final String FIELD_ARTICLE_ID = "article_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_QUANTITY = "quantity";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_BRAND = "brand";
    public static final String FIELD_BRAND_ID = "brand_id";
    public static final String FIELD_CATEGORIES = "categories";
    public static final String FIELD_CATEGORY_ID = "category_id";

    public static final int MINIMUM_ARTICLE_ID_VALUE = 1;
    public static final int MINIMUM_BRAND_ID_VALUE = 1;
    public static final int MINIMUM_CATEGORIES_LIST_VALUE = 1;
    public static final int MAXIMUM_CATEGORIES_LIST_VALUE = 3;
    public static final int MAXIMUM_CHARACTERS_ARTICLE_NAME = 100;
    public static final int MAXIMUM_CHARACTERS_ARTICLE_DESCRIPTION = 500;
    public static final int PRECISION_PRICE = 10;
    public static final int SCALE_PRICE = 2;

    public static final int MINIMUM_QUANTITY_VALUE = 1;
    public static final String MINIMUM_PRICE_VALUE_STRING = "0.01";
    public static final BigDecimal MINIMUM_PRICE_VALUE = BigDecimal.valueOf(0.01);

    public static final String INVALID_ARTICLE_ID = "Article id cannot be null or less than 1";

    public static final String INVALID_ARTICLE_NAME_SIZE = "Article name cannot be longer than 100 characters";
    public static final String INVALID_ARTICLE_NAME_NULL_EMPTY_OR_BLANK = "Article name cannot be null, empty or only blank characters";

    public static final String INVALID_ARTICLE_DESCRIPTION_SIZE = "Article description cannot be longer than 500 characters";
    public static final String INVALID_ARTICLE_DESCRIPTION_NULL_EMPTY_OR_BLANK = "Article description cannot be null, empty or only blank characters";

    public static final String INVALID_ARTICLE_QUANTITY = "Article quantity cannot be null or less than 1";
    public static final String INVALID_ARTICLE_PRICE = "Article price cannot be null or less than 0.01";
    public static final String INVALID_ARTICLE_BRAND = "Article brand id cannot be null or less than 1";
    public static final String INVALID_ARTICLE_CATEGORIES_NOT_NULL = "The list of category ids cannot be null";
    public static final String INVALID_ARTICLE_CATEGORIES = "Article must have between 1 and 3 categories";
    public static final String DUPLICATE_CATEGORIES = "The article category list cannot have duplicates";

    public static final String SORT_BY_ARTICLE = "name";
    public static final String SORT_BY_BRAND = "brand.name";
    public static final String SORT_BY_CATEGORY = "categories.name";

    private ArticleValidationMessages() {}
}
