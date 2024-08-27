package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.BrandExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.BrandAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.BrandBadRequestException;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.port.api.ICreateBrandServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateBrandPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsBrandByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.validation.BrandValidator;

import java.util.List;
import java.util.Map;

public class CreateBrandUseCase implements ICreateBrandServicePort {
    private final ICreateBrandPersistencePort  createBrandPersistencePort;
    private final IExistsBrandByNamePersistencePort existsBrandByNamePersistencePort;

    public CreateBrandUseCase(ICreateBrandPersistencePort createBrandPersistencePort, IExistsBrandByNamePersistencePort existsBrandByNamePersistencePort) {
        this.createBrandPersistencePort = createBrandPersistencePort;
        this.existsBrandByNamePersistencePort = existsBrandByNamePersistencePort;
    }

    @Override
    public void createBrand(Brand brand) {
        List<Map<String, String>> errors = BrandValidator.validate(brand);

        if (!errors.isEmpty()){
            throw new BrandBadRequestException(errors);
        }

        if (Boolean.TRUE.equals(existsBrandByNamePersistencePort.existsBrandByName(brand.getName()))) {
            throw new BrandAlreadyExistsException(BrandExceptionMessages.BRAND_ALREADY_EXISTS, brand.getName());
        }

        this.createBrandPersistencePort.createBrand(brand);
    }
}
