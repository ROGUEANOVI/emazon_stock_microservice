package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
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

    @Column(name = FIELD_NAME, nullable = false, unique = true, length = MAXIMUM_CHARACTERS_ARTICLE_NAME)
    private String name;

    @Column(name = FIELD_DESCRIPTION, nullable = false, length = MAXIMUM_CHARACTERS_ARTICLE_DESCRIPTION)
    private String description;

    @Column(name = FIELD_QUANTITY, nullable = false)
    private Long quantity;

    @Column(name = FIELD_PRICE, nullable = false, precision = PRECISION_PRICE, scale = SCALE_PRICE)
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
