package com.pragma.emazon.stock_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResponseCode {

    public static final String CODE_200 = "200";
    public static final String CODE_201 = "201";
    public static final String CODE_400 = "400";
    public static final String CODE_404 = "404";
    public static final String CODE_409 = "409";
}