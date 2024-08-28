package com.pragma.emazon.stock_microservice.domain.exception;

public class NoDataFoundArticleException extends RuntimeException {

    public NoDataFoundArticleException(String message) {
        super(message);
    }
}
