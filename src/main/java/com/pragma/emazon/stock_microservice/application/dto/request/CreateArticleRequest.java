package com.pragma.emazon.stock_microservice.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;
import java.util.List;

import static com.pragma.emazon.stock_microservice.domain.constant.ArticleValidationMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateArticleRequest {

    @Size(max = MAXIMUM_CHARACTERS_ARTICLE_NAME, message = INVALID_ARTICLE_NAME_SIZE)
    @NotBlank(message = INVALID_ARTICLE_NAME_NULL_EMPTY_OR_BLANK)
    private String name;

    @Size(max = MAXIMUM_CHARACTERS_ARTICLE_DESCRIPTION , message = INVALID_ARTICLE_DESCRIPTION_SIZE)
    @NotBlank(message = INVALID_ARTICLE_DESCRIPTION_NULL_EMPTY_OR_BLANK)
    private String description;

    @Min(value = MINIMUM_QUANTITY_VALUE, message = INVALID_ARTICLE_QUANTITY)
    @NotNull(message = INVALID_ARTICLE_QUANTITY)
    private Long quantity;

    @DecimalMin(value = MINIMUM_PRICE_VALUE_STRING, message = INVALID_ARTICLE_PRICE)
    @NotNull(message = INVALID_ARTICLE_PRICE)
    private BigDecimal price;

    @NotNull(message = INVALID_ARTICLE_BRAND)
    @Min(value = ONE_VALUE, message = INVALID_ARTICLE_BRAND)
    private Long brandId;

    @NotNull(message = INVALID_ARTICLE_CATEGORIES_NOT_NULL)
    @Size(min = ONE_VALUE, max = THREE_VALUE, message = INVALID_ARTICLE_CATEGORIES)
    @UniqueElements(message = DUPLICATE_CATEGORIES)
    private List<Long> categoryIds;
}
