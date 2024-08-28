package com.pragma.emazon.stock_microservice.domain.exception;

public class BrandAlreadyExistsException extends RuntimeException {

    public BrandAlreadyExistsException(String message, String name) {
        super(String.format(message, name));
    }
}
