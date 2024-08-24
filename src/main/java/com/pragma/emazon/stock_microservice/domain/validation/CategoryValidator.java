package com.pragma.emazon.stock_microservice.domain.validation;

import com.pragma.emazon.stock_microservice.domain.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.pragma.emazon.stock_microservice.domain.constant.CategoryValidationMessages.*;

public class CategoryValidator {
    private CategoryValidator() {}

    public static List<Map<String, String>> validate(Category category) {
        List<Map<String, String>> errors = new ArrayList<>();

        validateName(category, errors);
        validateDescription(category, errors);

        return errors;
    }

    private static void validateName(Category category, List<Map<String, String>> errors) {
        if (category.getName() == null) {
            errors.add(Map.of(FIELD_NAME, INVALID_CATEGORY_NAME_NULL));
        } else if (category.getName().isBlank()) {
            errors.add(Map.of(FIELD_NAME, INVALID_CATEGORY_NAME_EMPTY_OR_BLANK));
        } else if (category.getName().length() > MAXIMUM_CHARACTERS_CATEGORY_NAME) {
            errors.add(Map.of(FIELD_NAME, INVALID_CATEGORY_NAME_SIZE));
        }
    }

    private static void validateDescription(Category category, List<Map<String, String>> errors) {
        if (category.getDescription() == null) {
            errors.add(Map.of(FIELD_DESCRIPTION, INVALID_CATEGORY_DESCRIPTION_NULL));
        } else if (category.getDescription().isBlank()) {
            errors.add(Map.of(FIELD_DESCRIPTION, INVALID_CATEGORY_DESCRIPTION_EMPTY_OR_BLANK));
        } else if (category.getDescription().length() > MAXIMUM_CHARACTERS_CATEGORY_DESCRIPTION) {
            errors.add(Map.of(FIELD_DESCRIPTION, INVALID_CATEGORY_DESCRIPTION_SIZE));
        }
    }
}
