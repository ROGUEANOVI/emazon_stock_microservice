package com.pragma.emazon.stock_microservice.infrastructure.exceptionhandler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pragma.emazon.stock_microservice.domain.constant.GlobalMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Map<String, String>> handleJWTVerificationException(JWTVerificationException e) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(GlobalMessages.MESSAGE, e.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException e) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(GlobalMessages.MESSAGE, e.getMessage()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAuthorizationDeniedException(AuthorizationDeniedException e) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(GlobalMessages.MESSAGE, e.getMessage()));
    }
}
