package com.pragma.emazon.stock_microservice.domain.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message, Long id) {
        super(String.format(message, id));
    }
}
