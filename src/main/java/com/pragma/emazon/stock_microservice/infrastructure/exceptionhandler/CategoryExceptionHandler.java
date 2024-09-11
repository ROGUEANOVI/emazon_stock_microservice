package com.pragma.emazon.stock_microservice.infrastructure.exceptionhandler;

import com.pragma.emazon.stock_microservice.domain.constant.CategoryExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.stock_microservice.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryBadRequestException.class)
    public ResponseEntity<Map<String, String>> handleCategoryBadRequestException(CategoryBadRequestException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getErrors().forEach(error -> errors.put(error.keySet().iterator().next(), error.values().iterator().next()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateCategoryException(DuplicateCategoryException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlReadyExistsException(CategoryAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(CategoryNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(NoDataFoundCategoryException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundCategoryException(NoDataFoundCategoryException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }
}
