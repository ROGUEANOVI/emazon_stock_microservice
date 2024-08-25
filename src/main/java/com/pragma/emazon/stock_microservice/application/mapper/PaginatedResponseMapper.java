package com.pragma.emazon.stock_microservice.application.mapper;

import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaginatedResponseMapper {
    @Mapping(source = "content", target = "content")
    @Mapping(source = "pageNumber", target = "pageNumber")
    @Mapping(source = "pageSize", target = "pageSize")
    @Mapping(source = "totalElements", target = "totalElements")
    @Mapping(source = "totalPages", target = "totalPages")
    @Mapping(source = "firstPage", target = "firstPage")
    @Mapping(source = "lastPage", target = "lastPage")
    PaginatedResponse<CategoryResponse> toCategoriesPaginatedResponse(GenericPagination<Category> genericPagination);
}
