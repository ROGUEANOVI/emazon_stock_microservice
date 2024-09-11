package com.pragma.emazon.stock_microservice.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import static com.pragma.emazon.stock_microservice.domain.constant.BrandValidationMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBrandRequest {

    @Size(max = MAXIMUM_CHARACTERS_BRAND_NAME, message = INVALID_BRAND_NAME_SIZE)
    @NotBlank(message = INVALID_BRAND_NAME_NULL_EMPTY_OR_BLANK)
    private String name;

    @Size(max = MAXIMUM_CHARACTERS_BRAND_DESCRIPTION, message = INVALID_BRAND_DESCRIPTION_SIZE)
    @NotBlank(message = INVALID_BRAND_DESCRIPTION_NULL_EMPTY_OR_BLANK)
    private String description;
}
