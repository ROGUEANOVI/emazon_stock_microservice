package com.pragma.emazon.stock_microservice.domain.constant;

public class BrandExceptionMessages {
    public static final String MESSAGE = "message";
    public static final String BRAND_ALREADY_EXISTS = "Brand with name '%s' already exists";
    public static final String BRAND_NOT_FOUND = "Brand with id %s not found";
    public static final String NO_DATA_FOUND_BRAND = "No data found brand for the requested petition";

    private BrandExceptionMessages() {}
}
