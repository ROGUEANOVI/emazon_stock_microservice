package com.pragma.emazon.stock_microservice.domain.validation;

import static com.pragma.emazon.stock_microservice.domain.constant.ArticleValidationMessages.*;

public class ArticleSort {

    private ArticleSort() {}

    public static String resolveSortBy(String sortBy) {

        return switch (sortBy) {
            case FIELD_BRAND -> SORT_BY_BRAND;
            case FIELD_CATEGORIES -> SORT_BY_CATEGORY;
            default -> SORT_BY_ARTICLE;
        };
    }
}
