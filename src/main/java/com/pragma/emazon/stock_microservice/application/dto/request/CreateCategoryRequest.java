package com.pragma.emazon.stock_microservice.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.pragma.emazon.stock_microservice.domain.constant.CategoryValidationMessages.*;

@Getter
@Setter
public class CreateCategoryRequest {

    @Size(max = MAXIMUM_CHARACTERS_CATEGORY_NAME, message = INVALID_CATEGORY_NAME_SIZE)
    @NotBlank(message = INVALID_CATEGORY_NAME_NULL_EMPTY_OR_BLANK)
    private String name;

    @Size(max = MAXIMUM_CHARACTERS_CATEGORY_DESCRIPTION, message = INVALID_CATEGORY_DESCRIPTION_SIZE)
    @NotBlank(message = INVALID_CATEGORY_DESCRIPTION_NULL_EMPTY_OR_BLANK)
    private String description;

    public CreateCategoryRequest(String name, String description) {
        this.name = (name != null) ? name.trim() : null;
        this.description = (description != null) ? description.trim() : null;
    }
}
