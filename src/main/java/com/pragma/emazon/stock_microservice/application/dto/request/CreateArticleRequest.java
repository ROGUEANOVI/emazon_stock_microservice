package com.pragma.emazon.stock_microservice.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CreateArticleRequest {
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private Long brandId;
    private List<Long> categoryIds;

    public CreateArticleRequest(String name, String description, Long quantity, BigDecimal price, Long brandId, List<Long> categoryIds) {
        this.name = name.trim();
        this.description = description.trim();
        this.quantity = quantity;
        this.price = price;
        this.brandId = brandId;
        this.categoryIds = categoryIds;
    }
}
