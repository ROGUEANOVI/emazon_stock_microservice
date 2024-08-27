package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import static com.pragma.emazon.stock_microservice.domain.constant.ArticleValidationMessages.*;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = MAXIMUM_CHARACTERS_ARTICLE_NAME, message = INVALID_ARTICLE_NAME_SIZE)
    @NotBlank(message = INVALID_ARTICLE_NAME_EMPTY_OR_BLANK)
    @NotEmpty(message = INVALID_ARTICLE_NAME_EMPTY_OR_BLANK)
    @Column(name = FIELD_NAME, nullable = false, unique = true)
    private String name;

    @Size(max = MAXIMUM_CHARACTERS_ARTICLE_DESCRIPTION , message = INVALID_ARTICLE_DESCRIPTION_SIZE)
    @NotBlank(message = INVALID_ARTICLE_DESCRIPTION_EMPTY_OR_BLANK)
    @NotEmpty(message = INVALID_ARTICLE_DESCRIPTION_EMPTY_OR_BLANK)
    @Column(name = FIELD_DESCRIPTION, nullable = false)
    private String description;

    @Min(value = MINIMUM_QUANTITY_VALUE, message = INVALID_ARTICLE_QUANTITY)
    @Column(name = FIELD_QUANTITY, nullable = false)
    private Long quantity;

    @DecimalMin(value = MINIMUM_PRICE_VALUE, message = INVALID_ARTICLE_PRICE)
    @Column(name = FIELD_PRICE, nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = FIELD_BRAND_ID, nullable = false)
    private BrandEntity brand;

    @ManyToMany
    @JoinTable(
        name = TABLE_NAME_MANY_TO_MANY,
        joinColumns = @JoinColumn(name = FIELD_ARTICLE_ID),
        inverseJoinColumns = @JoinColumn(name = FIELD_CATEGORY_ID)
    )
    private List<CategoryEntity> categories;
}
