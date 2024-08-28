package com.pragma.emazon.stock_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BrandApiResponses {

    public static final String DESCRIPTION_200 = "Brand or brands ok";
    public static final String DESCRIPTION_201 = "Brand created";
    public static final String DESCRIPTION_409 = "Brand already exists";
    public static final String DESCRIPTION_400 = "Brand bad request";
    public static final String DESCRIPTION_404 = "Brand not found";

    public static final String SUMMARY_CREATE_BRAND = "Create new brand";
    public static final String DESCRIPTION_CREATE_BRAND = "Rest method for creating brand";
    public static final String SUMMARY_LIST_BRANDS = "List brands";
    public static final String DESCRIPTION_LIST_BRANDS = "Rest method for list brands";
}
