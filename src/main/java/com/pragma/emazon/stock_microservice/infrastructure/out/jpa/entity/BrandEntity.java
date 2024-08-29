package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
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

    @Column(name = FIELD_NAME, nullable = false, unique = true, length = MAXIMUM_CHARACTERS_BRAND_NAME)
    private String name;

    @Column(name =FIELD_DESCRIPTION, nullable = false, length = MAXIMUM_CHARACTERS_BRAND_DESCRIPTION)
    private String description;
}
