package com.pragma.emazon.stock_microservice.domain.exception;

import java.util.List;
import java.util.Map;

public class CategoryBadRequestException extends RuntimeException {

    private final List<Map<String, String>> errors;

    public CategoryBadRequestException(List<Map<String, String>> errors) {
        this.errors = errors;
    }

    public List<Map<String, String>> getErrors() {
        return errors;
    }
}
