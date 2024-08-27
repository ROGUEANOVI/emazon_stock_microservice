package com.pragma.emazon.stock_microservice.domain.exception;

public class DuplicateCategoryException extends RuntimeException {
    public DuplicateCategoryException(String message, Long id) {
        super(String.format(message, id));
    }
}
