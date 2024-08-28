package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.BrandExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundBrandException;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.api.IListBrandsServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListBrandsPersistencePort;

public class ListBrandsUseCase implements IListBrandsServicePort {

    private final IListBrandsPersistencePort listCategoriesPersistencePort;

    public ListBrandsUseCase(IListBrandsPersistencePort listCategoriesPersistencePort) {

        this.listCategoriesPersistencePort = listCategoriesPersistencePort;
    }

    @Override
    public GenericPagination<Brand> listBrands(Integer page, Integer size, String direction) {

        GenericPagination<Brand> listBrands = listCategoriesPersistencePort.listBrand(page, size, direction);

        if (listBrands.getTotalElements() == 0) {
            throw new NoDataFoundBrandException(BrandExceptionMessages.NO_DATA_FOUND_BRAND);
        }

        return listBrands;
    }
}
