package com.pragma.emazon.stock_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BrandTag {

    public static final String TAG_NAME = "Brand";
    public static final String TAG_DESCRIPTION = "Rest controller for brand";
    public static final String PATH = "/brands/";
}