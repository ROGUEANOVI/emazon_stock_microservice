package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import com.pragma.emazon.stock_microservice.domain.port.api.ICreateCategoryServicePort;
import com.pragma.emazon.stock_microservice.domain.port.api.IListCategoriesServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateCategoryPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsCategoryByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListCategoriesPersistencePort;
import com.pragma.emazon.stock_microservice.domain.usecase.CreateCategoryUseCase;
import com.pragma.emazon.stock_microservice.domain.usecase.ListCategoriesUseCase;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.CreateCategoryJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.ExistsCategoryByNameJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.ListCategoriesJpaAdapter;
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
    public ICreateCategoryPersistencePort createCategoryPersistencePort() {
        return new CreateCategoryJpaAdapter(categoryRepository, entityMapper);
    }

    @Bean
    public IExistsCategoryByNamePersistencePort existsCategoryByNamePersistencePort() {
        return new ExistsCategoryByNameJpaAdapter(categoryRepository);
    }

    @Bean
    public ICreateCategoryServicePort createCategoryServicePort() {
        return new CreateCategoryUseCase(createCategoryPersistencePort(), existsCategoryByNamePersistencePort());
    }

    @Bean
    public IListCategoriesPersistencePort listCategoriesPersistencePort() {
        return new ListCategoriesJpaAdapter(categoryRepository, pageCategoryEntityMapper);
    }

    @Bean
    public IListCategoriesServicePort listCategoriesServicePort() {
        return new ListCategoriesUseCase(listCategoriesPersistencePort());
    }
}
