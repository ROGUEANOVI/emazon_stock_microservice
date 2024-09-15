package com.pragma.emazon.stock_microservice.application.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UpdateQuantitySchemaDescriptions {

    public static final String UPDATE_QUANTITY_REQUEST = "Request body for updating article quantity";

    public static final String QUANTITY_DESCRIPTION = "The new quantity of the article to be updated";

    public static final String QUANTITY_EXAMPLE = "100";
}
