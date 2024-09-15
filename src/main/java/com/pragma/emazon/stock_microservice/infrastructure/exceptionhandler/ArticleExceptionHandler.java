package com.pragma.emazon.stock_microservice.infrastructure.exceptionhandler;

import com.pragma.emazon.stock_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.stock_microservice.domain.exception.ArticleAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.ArticleBadRequestException;
import com.pragma.emazon.stock_microservice.domain.exception.ArticleNotFoundException;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundArticleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ArticleExceptionHandler {

    @ExceptionHandler(ArticleBadRequestException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArticleException(ArticleBadRequestException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getErrors().forEach(error -> errors.put(error.keySet().iterator().next(), error.values().iterator().next()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ArticleAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleArticleAlreadyExistsException(ArticleAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(NoDataFoundArticleException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundArticleException(NoDataFoundArticleException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleArticleNotFoundException(ArticleNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }
}
