package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageBrandEntityMapper {
    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "pageNumber", source = "page", qualifiedByName = "mapPageNumber")
    @Mapping(target = "pageSize", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "firstPage", source = "page", qualifiedByName = "mapIsFirst")
    @Mapping(target = "lastPage", source = "page", qualifiedByName = "mapIsLast")
    GenericPagination<Brand> toGenericPaginationBrand(Page<BrandEntity> page);

    @Named("mapPageNumber")
    default Integer mapPageNumber(Page<BrandEntity> page) {
        return page.getNumber() + 1;
    }

    @Named("mapIsFirst")
    default Boolean mapIsFirst(Page<BrandEntity> page) {
        return !page.hasPrevious();
    }

    @Named("mapIsLast")
    default Boolean mapIsLast(Page<BrandEntity> page) {
        return !page.hasNext();
    }
}
