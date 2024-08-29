package com.pragma.emazon.stock_microservice.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.pragma.emazon.stock_microservice.domain.constant.BrandValidationMessages.*;

@Getter
@Setter
public class CreateBrandRequest {

    @Size(max = MAXIMUM_CHARACTERS_BRAND_NAME, message = INVALID_BRAND_NAME_SIZE)
    @NotBlank(message = INVALID_BRAND_NAME_NULL_EMPTY_OR_BLANK)
    private String name;

    @Size(max = MAXIMUM_CHARACTERS_BRAND_DESCRIPTION, message = INVALID_BRAND_DESCRIPTION_SIZE)
    @NotBlank(message = INVALID_BRAND_DESCRIPTION_NULL_EMPTY_OR_BLANK)
    private String description;

    public CreateBrandRequest(String name, String description) {
        this.name = (name != null) ? name.trim() : null;
        this.description = (description != null) ? description.trim() : null;
    }
}
