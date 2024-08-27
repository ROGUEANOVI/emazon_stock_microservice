package com.pragma.emazon.stock_microservice.domain.validation;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.pragma.emazon.stock_microservice.domain.constant.ArticleValidationMessages.*;

public class ArticleValidator {
    private ArticleValidator() {}

    public static List<Map<String, String>> validate(Article article){
        List<Map<String, String>> errors = new ArrayList<>();

        validateName(article.getName(), errors);
        validateDescription(article.getDescription(), errors);
        validateQuantity(article.getQuantity(), errors);
        validatePrice(article.getPrice(), errors);
        validateBrand(article.getBrand(), errors);
        validateCategories(article.getCategories(), errors);

        return errors;
    }

    private static void validateName (String name, List<Map<String, String>> errors){
        if (name == null) {
            errors.add(Map.of(FIELD_NAME, INVALID_ARTICLE_NAME_NULL));
        } else if (name.isBlank()) {
            errors.add(Map.of(FIELD_NAME, INVALID_ARTICLE_NAME_EMPTY_OR_BLANK));
        } else if (name.length() > MAXIMUM_CHARACTERS_ARTICLE_NAME) {
            errors.add(Map.of(FIELD_NAME, INVALID_ARTICLE_NAME_SIZE));
        }
    }

    private static void validateDescription(String description, List<Map<String, String>> errors) {
        if (description == null) {
            errors.add(Map.of(FIELD_DESCRIPTION, INVALID_ARTICLE_DESCRIPTION_NULL));
        } else if (description.isBlank()) {
            errors.add(Map.of(FIELD_DESCRIPTION, INVALID_ARTICLE_DESCRIPTION_EMPTY_OR_BLANK));
        } else if (description.length() > MAXIMUM_CHARACTERS_ARTICLE_DESCRIPTION) {
            errors.add(Map.of(FIELD_DESCRIPTION, INVALID_ARTICLE_DESCRIPTION_SIZE));
        }
    }

    private static void validateQuantity (Long quantity, List<Map<String, String>> errors){
        if (quantity == null || quantity <= 0) {
            errors.add(Map.of(FIELD_QUANTITY, INVALID_ARTICLE_QUANTITY));
        }
    }

    private static void validatePrice (BigDecimal price, List<Map<String, String>> errors){
        if (price == null || price.compareTo(BigDecimal.valueOf(0.01)) < 0) {
            errors.add(Map.of(FIELD_PRICE, INVALID_ARTICLE_PRICE));
        }
    }

    private static void validateBrand (Brand brand, List<Map<String, String>> errors){
        if (brand == null) {
            errors.add(Map.of(FIELD_BRAND, INVALID_ARTICLE_BRAND));
        }
    }

    private static void validateCategories (List <Category> categories, List<Map<String, String>> errors) {
        if (categories == null || categories.isEmpty() || categories.size() > 3) {
            errors.add(Map.of(FIELD_CATEGORIES, INVALID_ARTICLE_CATEGORIES));
        }
    }
}