package com.pragma.emazon.stock_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Regex {

    public static final String SORT_DIRECTION_REGEX = "(?i)^(asc|desc)$";

    public static final String SORT_BY_REGEX = "^(article|brand|category)$";

    public static final String SPLIT_REGEX = "\\.";
}
