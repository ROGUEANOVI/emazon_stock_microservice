package com.pragma.emazon.stock_microservice.application.dto.request;

import com.pragma.emazon.stock_microservice.application.constant.CreateCategoryRequestSchemaDescriptions;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import static com.pragma.emazon.stock_microservice.domain.constant.CategoryValidationMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = CreateCategoryRequestSchemaDescriptions.CREATE_CATEGORY_REQUEST_DESCRIPTION)
public class CreateCategoryRequest {

    @Schema(description = CreateCategoryRequestSchemaDescriptions.CATEGORY_NAME_DESCRIPTION, example = CreateCategoryRequestSchemaDescriptions.CATEGORY_NAME_EXAMPLE)
    @Size(max = MAXIMUM_CHARACTERS_CATEGORY_NAME, message = INVALID_CATEGORY_NAME_SIZE)
    @NotBlank(message = INVALID_CATEGORY_NAME_NULL_EMPTY_OR_BLANK)
    private String name;

    @Schema(description = CreateCategoryRequestSchemaDescriptions.CATEGORY_DESCRIPTION_DESCRIPTION, example = CreateCategoryRequestSchemaDescriptions.CATEGORY_DESCRIPTION_EXAMPLE)
    @Size(max = MAXIMUM_CHARACTERS_CATEGORY_DESCRIPTION, message = INVALID_CATEGORY_DESCRIPTION_SIZE)
    @NotBlank(message = INVALID_CATEGORY_DESCRIPTION_NULL_EMPTY_OR_BLANK)
    private String description;
}
