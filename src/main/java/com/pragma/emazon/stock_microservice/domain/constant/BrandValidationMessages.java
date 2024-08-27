package com.pragma.emazon.stock_microservice.domain.constant;

public class BrandValidationMessages {
    public static final String TABLE_NAME = "brand";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";

    public static final int MAXIMUM_CHARACTERS_BRAND_NAME = 50;
    public static final int MAXIMUM_CHARACTERS_BRAND_DESCRIPTION = 120;

    public static final String INVALID_BRAND_NAME_NULL = "Brand name cannot be null";
    public static final String INVALID_BRAND_NAME_SIZE = "Brand name cannot be longer than 50 characters";
    public static final String INVALID_BRAND_NAME_EMPTY_OR_BLANK = "Brand name cannot be empty or only blank characters";

    public static final String INVALID_BRAND_DESCRIPTION_NULL = "Brand description cannot be null";
    public static final String INVALID_BRAND_DESCRIPTION_SIZE = "Brand description cannot be longer than 120 characters";
    public static final String INVALID_BRAND_DESCRIPTION_EMPTY_OR_BLANK = "Brand description cannot be empty or only blank characters";

    private BrandValidationMessages() {}
}
