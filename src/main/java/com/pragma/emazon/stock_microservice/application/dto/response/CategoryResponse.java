package com.pragma.emazon.stock_microservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponse {

    Long id;
    private String name;
    private String description;
}
