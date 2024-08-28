package com.pragma.emazon.stock_microservice.application.mapper;

import com.pragma.emazon.stock_microservice.application.constant.MappingFields;
import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.BrandResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import org.mapstruct.Mapper;

@Mapper(componentModel = MappingFields.SPRING)
public interface PaginatedResponseMapper {

    PaginatedResponse<CategoryResponse> toCategoriesPaginatedResponse(GenericPagination<Category> genericPagination);

    PaginatedResponse<BrandResponse> toBrandsPaginatedResponse(GenericPagination<Brand> genericPagination);

    PaginatedResponse<ArticleResponse> toArticlesPaginatedResponse(GenericPagination<Article> genericPagination);
}
