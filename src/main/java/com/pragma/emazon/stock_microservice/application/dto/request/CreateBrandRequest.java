package com.pragma.emazon.stock_microservice.application.dto.request;

import com.pragma.emazon.stock_microservice.application.constant.CreateBrandRequestSchemaDescriptions;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import static com.pragma.emazon.stock_microservice.domain.constant.BrandValidationMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = CreateBrandRequestSchemaDescriptions.CREATE_BRAND_REQUEST_DESCRIPTION)
public class CreateBrandRequest {

    @Schema(description = CreateBrandRequestSchemaDescriptions.BRAND_NAME_DESCRIPTION, example = CreateBrandRequestSchemaDescriptions.BRAND_NAME_EXAMPLE)
    @Size(max = MAXIMUM_CHARACTERS_BRAND_NAME, message = INVALID_BRAND_NAME_SIZE)
    @NotBlank(message = INVALID_BRAND_NAME_NULL_EMPTY_OR_BLANK)
    private String name;

    @Schema(description = CreateBrandRequestSchemaDescriptions.BRAND_DESCRIPTION_DESCRIPTION, example = CreateBrandRequestSchemaDescriptions.BRAND_DESCRIPTION_EXAMPLE)
    @Size(max = MAXIMUM_CHARACTERS_BRAND_DESCRIPTION, message = INVALID_BRAND_DESCRIPTION_SIZE)
    @NotBlank(message = INVALID_BRAND_DESCRIPTION_NULL_EMPTY_OR_BLANK)
    private String description;
}
