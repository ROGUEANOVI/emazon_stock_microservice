package com.pragma.emazon.stock_microservice.domain.exception;

public class BrandNotFoundException extends RuntimeException {

    public BrandNotFoundException(String message, Long id) {
        super(String.format(message, id));
    }
}
