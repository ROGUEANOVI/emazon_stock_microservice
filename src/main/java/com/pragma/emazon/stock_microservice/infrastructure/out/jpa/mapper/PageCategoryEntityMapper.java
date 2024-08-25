package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring")
public interface PageCategoryEntityMapper {

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "pageNumber", source = "page", qualifiedByName = "mapPageNumberCategory")
    @Mapping(target = "pageSize", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "firstPage", source = "page", qualifiedByName = "mapIsFirstCategory")
    @Mapping(target = "lastPage", source = "page", qualifiedByName = "mapIsLastCategory")
    GenericPagination<Category> toGenericPaginationCategory(Page<CategoryEntity> page);

    @Named("mapPageNumberCategory")
    default Integer mapCategoriesPageNumber(Page<CategoryEntity> page) {
        return page.getNumber() + 1;
    }

    @Named("mapIsFirstCategory")
    default Boolean mapIsFirst(Page<CategoryEntity> page) {
        return !page.hasPrevious();
    }

    @Named("mapIsLastCategory")
    default Boolean mapIsLast(Page<CategoryEntity> page) {
        return !page.hasNext();
    }
}
