package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import com.pragma.emazon.stock_microservice.domain.port.api.ICreateArticleServicePort;
import com.pragma.emazon.stock_microservice.domain.port.api.IListArticlesServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.*;
import com.pragma.emazon.stock_microservice.domain.usecase.CreateArticleUseCase;
import com.pragma.emazon.stock_microservice.domain.usecase.ListArticlesUseCase;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.*;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageArticleEntityMapper;
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
    private final PageArticleEntityMapper pageArticleEntityMapper;

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

    @Bean
    public IListArticlesPersistencePort listArticlesPersistencePort() {

        return new ListArticlesJpaAdapter(articleRepository, pageArticleEntityMapper);
    }

    @Bean
    public IListArticlesServicePort listArticlesServicePort() {

        return new ListArticlesUseCase(listArticlesPersistencePort());
    }
}
