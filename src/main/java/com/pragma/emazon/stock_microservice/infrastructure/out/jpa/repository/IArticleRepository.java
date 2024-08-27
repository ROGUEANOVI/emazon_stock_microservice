package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository;

import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Boolean existsByName(String name);
}
