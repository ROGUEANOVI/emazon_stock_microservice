package com.pragma.emazon.stock_microservice.domain.validation;

import com.pragma.emazon.stock_microservice.domain.model.Brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.pragma.emazon.stock_microservice.domain.constant.BrandValidationMessages.*;

public class BrandValidator {

    private BrandValidator() {}

    public static List<Map<String, String>> validate(Brand brand) {

        List<Map<String, String>> errors = new ArrayList<>();

        validateName(brand, errors);
        validateDescription(brand, errors);

        return errors;
    }

    private static void validateName(Brand brand, List<Map<String, String>> errors) {

        if (brand.getName() == null) {
            errors.add(Map.of(FIELD_NAME, INVALID_BRAND_NAME_NULL));
        } else if (brand.getName().isBlank()) {
            errors.add(Map.of(FIELD_NAME, INVALID_BRAND_NAME_EMPTY_OR_BLANK));
        } else if (brand.getName().length() > MAXIMUM_CHARACTERS_BRAND_NAME) {
            errors.add(Map.of(FIELD_NAME, INVALID_BRAND_NAME_SIZE));
        }
    }

    private static void validateDescription(Brand brand, List<Map<String, String>> errors) {

        if (brand.getDescription() == null) {
            errors.add(Map.of(FIELD_DESCRIPTION, INVALID_BRAND_DESCRIPTION_NULL));
        } else if (brand.getDescription().isBlank()) {
            errors.add(Map.of(FIELD_DESCRIPTION, INVALID_BRAND_DESCRIPTION_EMPTY_OR_BLANK));
        } else if (brand.getDescription().length() > MAXIMUM_CHARACTERS_BRAND_DESCRIPTION) {
            errors.add(Map.of(FIELD_DESCRIPTION, INVALID_BRAND_DESCRIPTION_SIZE));
        }
    }
}
