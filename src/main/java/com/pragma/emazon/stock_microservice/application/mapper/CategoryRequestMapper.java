package com.pragma.emazon.stock_microservice.application.mapper;

import com.pragma.emazon.stock_microservice.application.constant.MappingFields;
import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingFields.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface CategoryRequestMapper {

    Category toCategory(CreateCategoryRequest createCategoryRequest);
}
