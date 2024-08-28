package com.pragma.emazon.stock_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CategoryApiResponses {

    public static final String DESCRIPTION_200 = "Category or categories ok";
    public static final String DESCRIPTION_201 = "Category created";
    public static final String DESCRIPTION_409 = "Category already exists";
    public static final String DESCRIPTION_400 = "Category bad request";
    public static final String DESCRIPTION_404 = "Category not found";

    public static final String SUMMARY_CREATE_CATEGORY = "Create new category";
    public static final String DESCRIPTION_CREATE_CATEGORY = "Rest method for creating category";
    public static final String SUMMARY_LIST_CATEGORIES = "List categories";
    public static final String DESCRIPTION_LIST_CATEGORIES = "Rest method for list categories";
}
