package com.pragma.emazon.stock_microservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {

    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private BrandDto brand;
    private List<CategoryDto> categories;
}
