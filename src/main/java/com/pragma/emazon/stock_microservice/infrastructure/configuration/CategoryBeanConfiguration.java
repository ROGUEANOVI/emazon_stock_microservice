package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import com.pragma.emazon.stock_microservice.domain.port.api.ICategoryServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICategoryPersistencePort;
import com.pragma.emazon.stock_microservice.domain.usecase.CategoryUseCase;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageCategoryEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryBeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final EntityMapper entityMapper;
    private final PageCategoryEntityMapper pageCategoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {

        return new CategoryJpaAdapter(categoryRepository, entityMapper, pageCategoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {

        return new CategoryUseCase(categoryPersistencePort());
    }
}
