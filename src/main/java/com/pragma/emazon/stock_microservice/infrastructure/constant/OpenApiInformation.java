package com.pragma.emazon.stock_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OpenApiInformation {

    public static final String TITLE = "Emazon Stock Management API";
    public static final String VERSION = "1.0.0";
    public static final String DESCRIPTION = "This is the Stock Management Microservice for the Emazon application. It provides endpoints for managing categories, brands, and articles within the inventory system. Use this API to handle stock-related operations, such as adding new items, updating existing ones, and retrieving information about categories and brands.";
    public static final String CONTACT_NAME = "Ovidio Romero";
    public static final String CONTACT_EMAIL = "ovidioromero66@gmail.com";
    public static final String CONTACT_URL = "https://www.linkedin.com/in/ovidio-romero/";
    public static final String REPOSITORY_URL = "https://github.com/ROGUEANOVI/emazon_stock_microservice";
    public static final String EXTERNAL_DOCUMENT_DESCRIPTION = "Emazon Stock Management API GitHub Repository";
}
