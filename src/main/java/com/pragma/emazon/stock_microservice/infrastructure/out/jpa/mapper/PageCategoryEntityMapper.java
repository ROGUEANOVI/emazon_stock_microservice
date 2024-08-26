package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring")
public interface PageCategoryEntityMapper {
    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "pageNumber", source = "page", qualifiedByName = "mapPageNumber")
    @Mapping(target = "pageSize", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "firstPage", source = "page", qualifiedByName = "mapIsFirst")
    @Mapping(target = "lastPage", source = "page", qualifiedByName = "mapIsLast")
    GenericPagination<Category> toGenericPaginationCategory(Page<CategoryEntity> page);

    @Named("mapPageNumber")
    default Integer mapPageNumber(Page<CategoryEntity> page) {
        return page.getNumber() + 1;
    }

    @Named("mapIsFirst")
    default Boolean mapIsFirst(Page<CategoryEntity> page) {
        return !page.hasPrevious();
    }

    @Named("mapIsLast")
    default Boolean mapIsLast(Page<CategoryEntity> page) {
        return !page.hasNext();
    }
}
