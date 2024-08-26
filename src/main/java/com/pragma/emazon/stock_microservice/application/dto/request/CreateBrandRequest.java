package com.pragma.emazon.stock_microservice.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBrandRequest {
    private String name;
    private String description;

    public CreateBrandRequest(String name, String description) {
        this.name = (name != null) ? name.trim() : null;
        this.description = (description != null) ? description.trim() : null;
    }
}
