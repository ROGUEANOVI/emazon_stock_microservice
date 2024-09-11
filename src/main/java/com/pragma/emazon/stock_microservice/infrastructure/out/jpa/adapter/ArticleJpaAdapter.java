package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.IArticlePersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.ArticleEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageArticleEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static com.pragma.emazon.stock_microservice.infrastructure.constant.PageMessages.MINIMUM_PAGE_NUMBER;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final EntityMapper entityMapper;
    private final PageArticleEntityMapper pageArticleEntityMapper;

    @Override
    @Transactional
    public void createArticle(Article article) {

        articleRepository.save(entityMapper.toArticleEntity(article));
    }

    @Override
    @Transactional(readOnly = true)
    public GenericPagination<Article> listArticles(Integer page, Integer size, String direction, String sortBy) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pagination = PageRequest.of(page - MINIMUM_PAGE_NUMBER, size, sort);

        Page<ArticleEntity> articleEntities = articleRepository.findAll(pagination);
        return pageArticleEntityMapper.toGenericPaginationArticle(articleEntities);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsArticleByName(String name) {

        return articleRepository.existsByName(name);
    }
}
