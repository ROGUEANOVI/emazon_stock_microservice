package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import com.pragma.emazon.stock_microservice.domain.port.api.ICreateBrandServicePort;
import com.pragma.emazon.stock_microservice.domain.port.api.IListBrandsServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateBrandPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsBrandByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListBrandsPersistencePort;
import com.pragma.emazon.stock_microservice.domain.usecase.CreateBrandUseCase;
import com.pragma.emazon.stock_microservice.domain.usecase.ListBrandsUseCase;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.CreateBrandJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.ExistsBrandByNameJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.ListBrandsJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageBrandEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BrandBeanConfiguration {

    private final IBrandRepository brandRepository;
    private final EntityMapper entityMapper;
    private final PageBrandEntityMapper pageBrandEntityMapper;

    @Bean
    public ICreateBrandPersistencePort createBrandPersistencePort() {

        return new CreateBrandJpaAdapter(brandRepository, entityMapper);
    }

    @Bean
    public IExistsBrandByNamePersistencePort existsBrandByNamePersistencePort() {

        return new ExistsBrandByNameJpaAdapter(brandRepository);
    }

    @Bean
    public ICreateBrandServicePort createBrandServicePort() {

        return new CreateBrandUseCase(createBrandPersistencePort(), existsBrandByNamePersistencePort());
    }

    @Bean
    public IListBrandsPersistencePort listBrandsPersistencePort() {

        return new ListBrandsJpaAdapter(brandRepository, pageBrandEntityMapper);
    }

    @Bean
    public IListBrandsServicePort listBrandsServicePort() {

        return new ListBrandsUseCase(listBrandsPersistencePort());
    }
}
