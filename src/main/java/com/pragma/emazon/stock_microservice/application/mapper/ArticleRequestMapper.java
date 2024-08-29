package com.pragma.emazon.stock_microservice.application.mapper;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;

import static com.pragma.emazon.stock_microservice.application.constant.MappingFields.*;
import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = SPRING)
public interface ArticleRequestMapper {

    @Mapping(target = ID, ignore = true)
    @Mapping(source = BRANDID, target = BRAND_ID)
    @Mapping(target = BRAND_NAME, constant = NAME)
    @Mapping(target = BRAND_DESCRIPTION, constant = DESCRIPTION)
    @Mapping(source = CATEGORY_IDS, target = CATEGORIES, qualifiedByName = MAP_TO_CATEGORIES)
    Article toArticle(CreateArticleRequest createArticleRequest);

    @Named(MAP_TO_CATEGORIES)
    default List<Category> mapToCategories(List<Long> categoryIds) {
        List<Category> categories = new ArrayList<>();

        if (!categoryIds.isEmpty()) {
            for (Long categoryId : categoryIds ) {
                categories.add(new Category(categoryId, NAME + categoryId.toString(), DESCRIPTION + categoryId.toString()));
            }
        }

        return categories;
    }
}
