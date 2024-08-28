package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.pragma.emazon.stock_microservice.domain.constant.BrandValidationMessages.*;

@Entity
@Table(name = TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Size(max = MAXIMUM_CHARACTERS_BRAND_NAME, message = INVALID_BRAND_NAME_SIZE)
    @NotBlank(message = INVALID_BRAND_NAME_EMPTY_OR_BLANK)
    @NotEmpty(message = INVALID_BRAND_NAME_EMPTY_OR_BLANK)
    @Column(name = FIELD_NAME, nullable = false, unique = true)
    private String name;

    @Size(max = MAXIMUM_CHARACTERS_BRAND_DESCRIPTION, message = INVALID_BRAND_DESCRIPTION_SIZE)
    @NotBlank(message = INVALID_BRAND_DESCRIPTION_EMPTY_OR_BLANK)
    @NotEmpty(message = INVALID_BRAND_DESCRIPTION_EMPTY_OR_BLANK)
    @Column(name =FIELD_DESCRIPTION, nullable = false)
    private String description;
}
