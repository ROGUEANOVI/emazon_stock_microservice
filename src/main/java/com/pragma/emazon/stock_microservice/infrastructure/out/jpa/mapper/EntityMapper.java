package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.ArticleEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface EntityMapper {
    CategoryEntity toCategoryEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);

    BrandEntity toBrandEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);

    ArticleEntity toArticleEntity(Article article);
}
