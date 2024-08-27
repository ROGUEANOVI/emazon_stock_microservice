package com.pragma.emazon.stock_microservice.application.mapper;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import static com.pragma.emazon.stock_microservice.domain.constant.CategoryValidationMessages.*;
import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(target = "brand.name", constant = "name")
    @Mapping(target = "brand.description", constant = "description")
    @Mapping(source = "categoryIds",target = "categories", qualifiedByName = "mapToCategories")
    Article toArticle(CreateArticleRequest createArticleRequest);

    @Named("mapToCategories")
    default List<Category> mapToCategories(List<Long> categoryIds) {
        List<Category> categories = new ArrayList<>();

        if (!categoryIds.isEmpty()) {
            for (Long categoryId : categoryIds ) {
                categories.add(new Category(categoryId, FIELD_NAME + categoryId.toString(), FIELD_DESCRIPTION + categoryId.toString()));
            }
        }

        return categories;
    }
}
