package com.pragma.emazon.stock_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SecurityMessages {

    public static final String AUTHORITIES = "authorities";

    public static final String BEARER = "Bearer ";

    public static final String INVALID_TOKEN = "Invalid token, not authorized";

    public static final String TOKEN_EXPIRED = "Invalid token, token expired";
}
