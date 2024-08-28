package com.pragma.emazon.stock_microservice.domain.constant;

public class CategoryExceptionMessages {

    public static final String MESSAGE = "message";
    public static final String CATEGORY_ALREADY_EXISTS = "Category with name '%s' already exists";
    public static final String CATEGORY_NOT_FOUND = "Category with id %s not found";
    public static final String DUPLICATE_CATEGORY = "Category with id %s duplicated";
    public static final String NO_DATA_FOUND_CATEGORY = "No data found category for the requested petition";

    private CategoryExceptionMessages() {}
}
