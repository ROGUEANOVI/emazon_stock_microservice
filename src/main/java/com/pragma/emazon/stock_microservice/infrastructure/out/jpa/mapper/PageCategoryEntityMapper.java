package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import static com.pragma.emazon.stock_microservice.application.constant.MappingFields.*;


@Mapper(componentModel =SPRING)
public interface PageCategoryEntityMapper {

    @Mapping(target = CONTENT, source = PAGE_CONTENT)
    @Mapping(target = PAGE_NUMBER, source = PAGE, qualifiedByName = MAP_PAGE_NUMBER)
    @Mapping(target = PAGESIZE, source = PAGE_SIZE)
    @Mapping(target = TOTAL_ELEMENTS, source = PAGE_TOTAL_ELEMENTS)
    @Mapping(target = TOTAL_PAGES, source = PAGE_TOTAL_PAGES)
    @Mapping(target = FIRST_PAGE, source = PAGE, qualifiedByName = MAP_IS_FIRST)
    @Mapping(target = LAST_PAGE, source = PAGE, qualifiedByName = MAP_IS_LAST)
    GenericPagination<Category> toGenericPaginationCategory(Page<CategoryEntity> page);

    @Named(MAP_PAGE_NUMBER)
    default Integer mapPageNumber(Page<CategoryEntity> page) {
        return page.getNumber() + ONE_VALUE;
    }

    @Named(MAP_IS_FIRST)
    default Boolean mapIsFirst(Page<CategoryEntity> page) {
        return !page.hasPrevious();
    }

    @Named(MAP_IS_LAST)
    default Boolean mapIsLast(Page<CategoryEntity> page) {
        return !page.hasNext();
    }
}
