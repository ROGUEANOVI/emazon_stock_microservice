package com.pragma.emazon.stock_microservice.infrastructure.exceptionhandler;

import com.pragma.emazon.stock_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.stock_microservice.domain.exception.BrandAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.BrandBadRequestException;
import com.pragma.emazon.stock_microservice.domain.exception.BrandNotFoundException;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundBrandException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BrandExceptionHandler {

    @ExceptionHandler(BrandBadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBrandBadRequestException(BrandBadRequestException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getErrors().forEach(error -> errors.put(error.keySet().iterator().next(), error.values().iterator().next()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlReadyExistsException(BrandAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBrandNotFoundException(BrandNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(NoDataFoundBrandException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundBrandException(NoDataFoundBrandException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }
}
