package com.pragma.emazon.stock_microservice.domain.exception;

public class NoDataFoundBrandException extends RuntimeException {
    public NoDataFoundBrandException(String message) {
        super(message);
    }
}
