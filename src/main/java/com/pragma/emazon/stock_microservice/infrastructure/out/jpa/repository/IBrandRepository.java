package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository;

import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    Boolean existsByName(String name);
}
