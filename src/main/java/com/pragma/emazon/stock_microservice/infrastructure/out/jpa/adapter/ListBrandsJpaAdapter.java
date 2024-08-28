package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListBrandsPersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageBrandEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.pragma.emazon.stock_microservice.infrastructure.constant.PropertyNames.*;

@RequiredArgsConstructor
public class ListBrandsJpaAdapter implements IListBrandsPersistencePort {

    private final IBrandRepository brandRepository;
    private final PageBrandEntityMapper pageCategoryEntityMapper;

    @Override
    public GenericPagination<Brand> listBrand(Integer page, Integer size, String direction) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), NAME);
        Pageable pagination = PageRequest.of(page - ONE_VALUE, size, sort);

        return pageCategoryEntityMapper.toGenericPaginationBrand(brandRepository.findAll(pagination));
    }
}
