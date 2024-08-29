package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.BrandExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.BrandAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundBrandException;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.api.IBrandServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IBrandPersistencePort;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void createBrand(Brand brand) {

        if (Boolean.TRUE.equals(brandPersistencePort.existsBrandByName(brand.getName()))) {
            throw new BrandAlreadyExistsException(BrandExceptionMessages.BRAND_ALREADY_EXISTS, brand.getName());
        }

        brandPersistencePort.createBrand(brand);
    }

    @Override
    public GenericPagination<Brand> listBrands(Integer page, Integer size, String direction) {

        GenericPagination<Brand> listBrands = brandPersistencePort.listBrands(page, size, direction);

        if (listBrands.getTotalElements() == 0) {
            throw new NoDataFoundBrandException(BrandExceptionMessages.NO_DATA_FOUND_BRAND);
        }

        return listBrands;
    }
}
