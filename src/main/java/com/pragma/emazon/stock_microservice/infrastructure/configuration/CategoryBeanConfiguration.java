package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import com.pragma.emazon.stock_microservice.domain.port.api.ICreateCategoryServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateCategoryPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsCategoryByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.usecase.CreateCategoryUseCase;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.CreateCategoryJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.ExistsCategoryByNameJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryBeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICreateCategoryPersistencePort createCategoryPersistencePort() {
        return new CreateCategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public IExistsCategoryByNamePersistencePort existsCategoryByNamePersistencePort() {
        return new ExistsCategoryByNameJpaAdapter(categoryRepository);
    }

    @Bean
    public ICreateCategoryServicePort createCategoryServicePort() {
        return new CreateCategoryUseCase(createCategoryPersistencePort(), existsCategoryByNamePersistencePort());
    }
}
