package com.pragma.emazon.stock_microservice.domain.constant;

public class CategoryValidationMessages {

    public static final String TABLE_NAME = "category";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";

    public static final int MAXIMUM_CHARACTERS_CATEGORY_NAME = 50;
    public static final int MAXIMUM_CHARACTERS_CATEGORY_DESCRIPTION = 90;

    public static final String INVALID_CATEGORY_NAME_NULL = "Category name cannot be null";
    public static final String INVALID_CATEGORY_NAME_SIZE = "Category name cannot be longer than 50 characters";
    public static final String INVALID_CATEGORY_NAME_EMPTY_OR_BLANK = "Category name cannot be empty or only blank characters";

    public static final String INVALID_CATEGORY_DESCRIPTION_NULL = "Category description cannot be null";
    public static final String INVALID_CATEGORY_DESCRIPTION_SIZE = "Category description cannot be longer than 90 characters";
    public static final String INVALID_CATEGORY_DESCRIPTION_EMPTY_OR_BLANK = "Category description cannot be empty or only blank characters";

    private CategoryValidationMessages() {}
}
