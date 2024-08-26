package com.pragma.emazon.stock_microservice.domain.exception;

import java.util.List;
import java.util.Map;

public class BrandBadRequestException extends RuntimeException {
    private final List<Map<String, String>> errors;

    public BrandBadRequestException(List<Map<String, String>> errors) {
        this.errors = errors;
    }

    public List<Map<String, String>> getErrors() {
        return errors;
    }
}
