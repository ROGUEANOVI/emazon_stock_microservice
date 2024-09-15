package com.pragma.emazon.stock_microservice.domain.exception;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message, Long id) {

        super(String.format(message, id));
    }
}
