package com.pragma.emazon.stock_microservice.application.mapper;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateBrandRequest;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface BrandRequestMapper {
    Brand toBrand(CreateBrandRequest createBrandRequest);
}
