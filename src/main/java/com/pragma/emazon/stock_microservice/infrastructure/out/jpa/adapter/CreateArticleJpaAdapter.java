package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateArticlePersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateArticleJpaAdapter implements ICreateArticlePersistencePort {
    private final IArticleRepository articleRepository;
    private final EntityMapper entityMapper;

    @Override
    public void createArticle(Article article) {
        articleRepository.save(entityMapper.toArticleEntity(article));
    }
}
