package com.pragma.emazon.stock_microservice.domain.exception;

public class NoDataFoundCategoryException extends RuntimeException {

    public NoDataFoundCategoryException(String message) {
        super(message);
    }
}
