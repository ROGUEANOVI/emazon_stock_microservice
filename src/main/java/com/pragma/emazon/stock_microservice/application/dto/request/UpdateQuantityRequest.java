package com.pragma.emazon.stock_microservice.application.dto.request;

import com.pragma.emazon.stock_microservice.application.constant.UpdateQuantitySchemaDescriptions;
import com.pragma.emazon.stock_microservice.domain.constant.ArticleValidationMessages;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = UpdateQuantitySchemaDescriptions.UPDATE_QUANTITY_REQUEST)
public class UpdateQuantityRequest {

    @Schema(description = UpdateQuantitySchemaDescriptions.QUANTITY_DESCRIPTION, example = UpdateQuantitySchemaDescriptions.QUANTITY_EXAMPLE)
    @Min(value = ArticleValidationMessages.MINIMUM_QUANTITY_VALUE, message = ArticleValidationMessages.INVALID_ARTICLE_QUANTITY)
    @NotNull(message = ArticleValidationMessages.INVALID_ARTICLE_QUANTITY)
    private Integer quantity;
}
