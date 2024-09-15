package com.pragma.emazon.stock_microservice.application.dto.request;

import com.pragma.emazon.stock_microservice.application.constant.CreateArticleRequestSchemaDescriptions;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = CreateArticleRequestSchemaDescriptions.CREATE_ARTICLE_REQUEST_DESCRIPTION)
public class CreateArticleRequest {

    @Schema(description = CreateArticleRequestSchemaDescriptions.ARTICLE_NAME_DESCRIPTION, example = CreateArticleRequestSchemaDescriptions.ARTICLE_NAME_EXAMPLE)
    @Size(max = MAXIMUM_CHARACTERS_ARTICLE_NAME, message = INVALID_ARTICLE_NAME_SIZE)
    @NotBlank(message = INVALID_ARTICLE_NAME_NULL_EMPTY_OR_BLANK)
    private String name;

    @Schema(description = CreateArticleRequestSchemaDescriptions.ARTICLE_DESCRIPTION_DESCRIPTION, example = CreateArticleRequestSchemaDescriptions.ARTICLE_DESCRIPTION_EXAMPLE)
    @Size(max = MAXIMUM_CHARACTERS_ARTICLE_DESCRIPTION , message = INVALID_ARTICLE_DESCRIPTION_SIZE)
    @NotBlank(message = INVALID_ARTICLE_DESCRIPTION_NULL_EMPTY_OR_BLANK)
    private String description;

    @Schema(description = CreateArticleRequestSchemaDescriptions.ARTICLE_QUANTITY_DESCRIPTION, example = CreateArticleRequestSchemaDescriptions.ARTICLE_QUANTITY_EXAMPLE)
    @Min(value = MINIMUM_QUANTITY_VALUE, message = INVALID_ARTICLE_QUANTITY)
    @NotNull(message = INVALID_ARTICLE_QUANTITY)
    private Integer quantity;

    @Schema(description = CreateArticleRequestSchemaDescriptions.ARTICLE_PRICE_DESCRIPTION, example = CreateArticleRequestSchemaDescriptions.ARTICLE_PRICE_EXAMPLE)
    @DecimalMin(value = MINIMUM_PRICE_VALUE_STRING, message = INVALID_ARTICLE_PRICE)
    @NotNull(message = INVALID_ARTICLE_PRICE)
    private BigDecimal price;

    @Schema(description = CreateArticleRequestSchemaDescriptions.ARTICLE_BRAND_ID_DESCRIPTION, example = CreateArticleRequestSchemaDescriptions.ARTICLE_BRAND_ID_EXAMPLE)
    @NotNull(message = INVALID_ARTICLE_BRAND)
    @Min(value = MINIMUM_BRAND_ID_VALUE, message = INVALID_ARTICLE_BRAND)
    private Long brandId;

    @Schema(description = CreateArticleRequestSchemaDescriptions.ARTICLE_CATEGORIES_DESCRIPTION, example = CreateArticleRequestSchemaDescriptions.ARTICLE_CATEGORIES_EXAMPLE)
    @NotNull(message = INVALID_ARTICLE_CATEGORIES_NOT_NULL)
    @Size(min = MINIMUM_CATEGORIES_LIST_VALUE, max = MAXIMUM_CATEGORIES_LIST_VALUE, message = INVALID_ARTICLE_CATEGORIES)
    @UniqueElements(message = DUPLICATE_CATEGORIES)
    private List<Long> categoryIds;
}
