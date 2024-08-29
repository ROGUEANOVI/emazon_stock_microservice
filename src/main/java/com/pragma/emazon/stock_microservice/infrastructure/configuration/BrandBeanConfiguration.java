package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import com.pragma.emazon.stock_microservice.domain.port.api.IBrandServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IBrandPersistencePort;
import com.pragma.emazon.stock_microservice.domain.usecase.BrandUseCase;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.BrandJpaAdapter;
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
    public IBrandPersistencePort brandPersistencePort() {

        return new BrandJpaAdapter(brandRepository, entityMapper, pageBrandEntityMapper);
    }



    @Bean
    public IBrandServicePort brandServicePort() {

        return new BrandUseCase(brandPersistencePort());
    }
}
