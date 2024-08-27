package com.pragma.emazon.stock_microservice.domain.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String message, String name) {
        super(String.format(message, name));
    }
}
