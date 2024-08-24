package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository;

import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
}
