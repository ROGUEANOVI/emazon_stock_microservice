package com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CategoryApiResponses {
    public static final String OPERATION_SUMMARY = "Create new category";
    public static final String OPERATION_DESCRIPTION = "Rest method for creating category";
    public static final String DESCRIPTION_201 = "Category created";
    public static final String DESCRIPTION_409 = "Category already exists";
    public static final String DESCRIPTION_400 = "Category bad request";

}
