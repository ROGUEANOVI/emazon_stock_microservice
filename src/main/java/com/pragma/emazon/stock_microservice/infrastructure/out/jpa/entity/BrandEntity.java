package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity;

import com.pragma.emazon.stock_microservice.domain.constant.BrandValidationMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brand")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Size(max = BrandValidationMessages.MAXIMUM_CHARACTERS_BRAND_NAME)
    @NotBlank(message = BrandValidationMessages.INVALID_BRAND_NAME_EMPTY_OR_BLANK)
    @NotEmpty(message = BrandValidationMessages.INVALID_BRAND_NAME_EMPTY_OR_BLANK)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Size(max = BrandValidationMessages.MAXIMUM_CHARACTERS_BRAND_DESCRIPTION)
    @NotBlank(message = BrandValidationMessages.INVALID_BRAND_DESCRIPTION_EMPTY_OR_BLANK)
    @NotEmpty(message = BrandValidationMessages.INVALID_BRAND_DESCRIPTION_EMPTY_OR_BLANK)
    @Column(name = "description", nullable = false)
    private String description;
}
