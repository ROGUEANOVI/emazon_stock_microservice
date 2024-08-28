package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.constant.PropertyNames;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.ArticleEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageArticleEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ListArticlesJpaAdapterTest {

    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private PageArticleEntityMapper pageArticleEntityMapper;

    @InjectMocks
    private ListArticlesJpaAdapter listArticlesJpaAdapter;

    private Page<ArticleEntity> articleEntityPage;
    private GenericPagination<Article> genericPagination;
    private Pageable pageable;

    @BeforeEach
    void setUp() {

        Article article = new Article(null, "Article 45", "description ae", 10L, BigDecimal.valueOf(10.0), new Brand(1L, "Koaj", "description koaj"), List.of());
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, PropertyNames.NAME));
        genericPagination = new GenericPagination<>(
                List.of(article), // Content
                0, // Page Number
                10, // Page Size
                1L, // Total Elements
                1, // Total Pages
                true, // Is First
                true // Is Last
        );

        ArticleEntity articleEntity = new ArticleEntity(
                1L,
                "Article dghg",
                "description 2",
                10L,
                BigDecimal.valueOf(10.0),
                new BrandEntity(1L, "Koaj", "description koaj"),
                List.of(new CategoryEntity(1L, "Electronics", "Gadgets and devices"))
        );

        articleEntityPage = new PageImpl<>( List.of(articleEntity), pageable, 1L );
    }

    @Test
    void listBrandsShouldReturnCustomPageWhenDataIsAvailable() {

        // Arrange
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, PropertyNames.NAME));
        when(articleRepository.findAll(pageable)).thenReturn(articleEntityPage);
        when(pageArticleEntityMapper.toGenericPaginationArticle(articleEntityPage)).thenReturn(genericPagination);

        // Act
        GenericPagination<Article> result = listArticlesJpaAdapter.listArticles(1, 10, "ASC", "name");

        // Assert
        assertEquals(genericPagination, result);
        verify(articleRepository, times(1)).findAll(any(Pageable.class));
        verify(pageArticleEntityMapper, times(1)).toGenericPaginationArticle(any(Page.class));
    }

    @Test
    void listBrandsShouldUseCorrectSortingWhenDirectionIsGiven() {

        // Arrange
        when(articleRepository.findAll(any(Pageable.class))).thenReturn(articleEntityPage);
        when(pageArticleEntityMapper.toGenericPaginationArticle(any(Page.class))).thenReturn(genericPagination);

        // Act
        GenericPagination<Article> result = listArticlesJpaAdapter.listArticles(1, 10, "DESC", "name");

        // Assert
        verify(articleRepository).findAll(pageable);
        verify(pageArticleEntityMapper).toGenericPaginationArticle(articleEntityPage);
        assertEquals(genericPagination, result);
    }
}
