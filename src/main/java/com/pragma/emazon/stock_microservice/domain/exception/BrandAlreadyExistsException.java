package com.pragma.emazon.stock_microservice.domain.exception;

public class BrandAlreadyExistsException extends RuntimeException {
    public BrandAlreadyExistsException(String message) {
        super(message);
    }
}
