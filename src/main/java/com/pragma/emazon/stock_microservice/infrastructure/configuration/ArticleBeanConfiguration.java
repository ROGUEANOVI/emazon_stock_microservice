package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import com.pragma.emazon.stock_microservice.domain.port.api.IArticleServicePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.*;
import com.pragma.emazon.stock_microservice.domain.usecase.ArticleUseCase;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter.*;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageArticleEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ArticleBeanConfiguration {

    private final IArticleRepository articleRepository;
    private final EntityMapper entityMapper;
    private final PageArticleEntityMapper pageArticleEntityMapper;
    private final BrandBeanConfiguration brandBeanConfiguration;
    private final CategoryBeanConfiguration categoryBeanConfiguration;

    @Bean
    public IArticlePersistencePort articlePersistencePort() {

        return new ArticleJpaAdapter(articleRepository, entityMapper, pageArticleEntityMapper);
    }

    @Bean
    public IArticleServicePort articleServicePort(
            IBrandPersistencePort brandPersistencePort,
            ICategoryPersistencePort categoryPersistencePort) {

        return new ArticleUseCase(
            articlePersistencePort(),
            brandPersistencePort,
            categoryPersistencePort
        );
    }
}
