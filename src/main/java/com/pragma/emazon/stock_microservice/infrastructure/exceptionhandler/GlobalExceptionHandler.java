package com.pragma.emazon.stock_microservice.infrastructure.exceptionhandler;

import com.pragma.emazon.stock_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.stock_microservice.infrastructure.constant.Regex;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String[] pathElements = violation.getPropertyPath().toString().split(Regex.SPLIT_REGEX);
            String paramName = pathElements[pathElements.length - GlobalMessages.LAST_ELEMENT_INDEX];
            String message = violation.getMessage();
            errors.put(paramName, message);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
