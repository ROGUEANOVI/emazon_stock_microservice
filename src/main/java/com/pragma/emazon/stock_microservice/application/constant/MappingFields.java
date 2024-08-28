package com.pragma.emazon.stock_microservice.application.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MappingFields {

    public static final String SPRING = "spring";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY_IDS = "categoryIds";
    public static final String CATEGORIES = "categories";
    public static final String MAP_TO_CATEGORIES = "mapToCategories";
    public static final String BRANDID = "brandId";
    public static final String BRAND_ID = "brand.id";
    public static final String BRAND_NAME = "brand.name";
    public static final String BRAND_DESCRIPTION = "brand.description";

    public static final String CONTENT = "content";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGESIZE = "pageSize";
    public static final String TOTAL_ELEMENTS = "totalElements";
    public static final String TOTAL_PAGES = "totalPages";
    public static final String FIRST_PAGE = "firstPage";
    public static final String LAST_PAGE = "lastPage";

    public static final String PAGE = "page";
    public static final String PAGE_CONTENT = "page.content";
    public static final String PAGE_SIZE = "page.size";
    public static final String PAGE_TOTAL_ELEMENTS = "page.totalElements";
    public static final String PAGE_TOTAL_PAGES = "page.totalPages";

    public static final String MAP_PAGE_NUMBER = "mapPageNumber";
    public static final String MAP_IS_FIRST = "mapIsFirst";
    public static final String MAP_IS_LAST = "mapIsLast";

    public static final int ONE_VALUE = 1;
}
