package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity;

import com.pragma.emazon.stock_microservice.domain.constant.CategoryValidationMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Size(max = CategoryValidationMessages.MAXIMUM_CHARACTERS_CATEGORY_NAME)
    @NotBlank(message = CategoryValidationMessages.INVALID_CATEGORY_NAME_EMPTY_OR_BLANK)
    @NotEmpty(message = CategoryValidationMessages.INVALID_CATEGORY_NAME_EMPTY_OR_BLANK)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Size(max = CategoryValidationMessages.MAXIMUM_CHARACTERS_CATEGORY_DESCRIPTION)
    @NotBlank(message = CategoryValidationMessages.INVALID_CATEGORY_DESCRIPTION_EMPTY_OR_BLANK)
    @NotEmpty(message = CategoryValidationMessages.INVALID_CATEGORY_DESCRIPTION_EMPTY_OR_BLANK)
    @Column(name = "description", nullable = false)
    private String description;
}
