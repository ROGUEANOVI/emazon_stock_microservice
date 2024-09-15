package com.pragma.emazon.stock_microservice.application.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateBrandRequestSchemaDescriptions {

    public static final String CREATE_BRAND_REQUEST_DESCRIPTION = "Request body to create a brand";

    public static final String BRAND_NAME_DESCRIPTION = "Name of the brand to be created";

    public static final String BRAND_NAME_EXAMPLE = "Acme Inc.";

    public static final String BRAND_DESCRIPTION_DESCRIPTION = "Description of the brand";

    public static final String BRAND_DESCRIPTION_EXAMPLE = "Leading manufacturer of quality products.";
}
