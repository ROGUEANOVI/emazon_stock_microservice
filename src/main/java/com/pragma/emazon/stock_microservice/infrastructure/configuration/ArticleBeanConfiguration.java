package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import com.pragma.emazon.stock_microservice.domain.port.api.ICreateArticleServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateArticlePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsArticleByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IFindBrandByIdPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IFindCategoryByIdPersistencePort;
import com.pragma.emazon.stock_microservice.domain.usecase.CreateArticleUseCase;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.CreateArticleJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.ExistsArticleByNameJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.FindBrandByIdJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.FindCategoryByIdJpaAdapter;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IArticleRepository;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ArticleBeanConfiguration {
    private final IArticleRepository articleRepository;
    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;
    private final EntityMapper entityMapper;

    @Bean
    public ICreateArticlePersistencePort createArticlePersistencePort() {
        return new CreateArticleJpaAdapter(articleRepository, entityMapper);
    }

    @Bean
    public IExistsArticleByNamePersistencePort existsArticleByNamePersistencePort() {
        return new ExistsArticleByNameJpaAdapter(articleRepository);
    }

    @Bean
    public IFindBrandByIdPersistencePort findBrandByIdPersistencePort() {
        return new FindBrandByIdJpaAdapter(brandRepository, entityMapper);
    }

    @Bean
    public IFindCategoryByIdPersistencePort findCategoryByIdPersistencePort() {
        return new FindCategoryByIdJpaAdapter(categoryRepository, entityMapper);
    }

    @Bean
    public ICreateArticleServicePort createArticleServicePort() {
        return new CreateArticleUseCase(
            createArticlePersistencePort(),
            existsArticleByNamePersistencePort(),
            findBrandByIdPersistencePort(),
            findCategoryByIdPersistencePort()
        );
    }
}
