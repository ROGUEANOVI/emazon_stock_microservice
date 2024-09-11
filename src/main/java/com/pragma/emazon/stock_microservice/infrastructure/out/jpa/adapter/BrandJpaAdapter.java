package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.IBrandPersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageBrandEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.pragma.emazon.stock_microservice.infrastructure.constant.PageMessages.*;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;

    private final EntityMapper entityMapper;

    private final PageBrandEntityMapper pageCategoryEntityMapper;

    @Override
    @Transactional
    public void createBrand(Brand brand) {

        brandRepository.save(entityMapper.toBrandEntity(brand));
    }

    @Override
    @Transactional(readOnly = true)
    public GenericPagination<Brand> listBrands(Integer page, Integer size, String direction) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), PROPERTY_NAME);
        Pageable pagination = PageRequest.of(page - MINIMUM_PAGE_NUMBER, size, sort);

        return pageCategoryEntityMapper.toGenericPaginationBrand(brandRepository.findAll(pagination));
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsBrandByName(String name) {

        return brandRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Brand> findBrandById(Long id) {

        return brandRepository.findById(id).map(entityMapper::toBrand);
    }
}
