package com.pragma.emazon.stock_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CategoryTag {

    public static final String TAG_NAME = "Category";
    public static final String TAG_DESCRIPTION = "Rest controller for category";
    public static final String PATH = "/categories/";
}
