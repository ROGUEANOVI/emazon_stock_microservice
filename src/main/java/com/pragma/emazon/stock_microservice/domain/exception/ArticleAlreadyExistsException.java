package com.pragma.emazon.stock_microservice.domain.exception;

public class ArticleAlreadyExistsException extends RuntimeException {
    public ArticleAlreadyExistsException(String message, String name) {
        super(String.format(message, name));
    }
}
