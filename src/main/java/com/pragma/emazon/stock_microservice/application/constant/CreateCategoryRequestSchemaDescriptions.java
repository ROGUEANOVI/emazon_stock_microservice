package com.pragma.emazon.stock_microservice.application.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateCategoryRequestSchemaDescriptions {

    public static final String CREATE_CATEGORY_REQUEST_DESCRIPTION = "Request body to create a category";

    public static final String CATEGORY_NAME_DESCRIPTION = "Name of the category to be created";

    public static final String CATEGORY_NAME_EXAMPLE = "Electronics";

    public static final String CATEGORY_DESCRIPTION_DESCRIPTION = "Description of the category";

    public static final String CATEGORY_DESCRIPTION_EXAMPLE = "Category for all electronic products.";
}
