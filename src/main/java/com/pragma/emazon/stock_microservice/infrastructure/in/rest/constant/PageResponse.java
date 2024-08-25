package com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PageResponse {
    public static final String DEFAULT_PAGE_VALUE = "1";
    public static final String DEFAULT_PAGE_SIZE_VALUE = "10";
    public static final String DEFAULT_SORT_DIRECTION_VALUE = "asc";

    public static final String INVALID_PAGE_VALUE = "Pages number must be 1 or greater";
    public static final String INVALID_PAGE_ITEMS_NUMBER  = "Elements number of page must be 1 or greater";
    public static final String INVALID_SORT_DIRECTION  = "Sort direction must be asc or desc";
}
