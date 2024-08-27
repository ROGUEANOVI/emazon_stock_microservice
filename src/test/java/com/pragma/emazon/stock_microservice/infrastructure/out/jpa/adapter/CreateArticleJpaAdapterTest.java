package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.ArticleEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class CreateArticleJpaAdapterTest {
    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private CreateArticleJpaAdapter createArticleJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createArticleShouldCallSaveOnRepository() {
        // Arrange
        Article article = new Article(
                null,
            "Test Brand",
                "Test Description",
                10L,
                BigDecimal.valueOf(10.0),
                new Brand(1L, "Test Brand", "Test Description"),
                List.of(new Category(1L, "sgg", "efsdgffs"))
        );
        ArticleEntity articleEntity = new ArticleEntity();

        when(entityMapper.toArticleEntity(any(Article.class))).thenReturn(articleEntity);

        // Act
        createArticleJpaAdapter.createArticle(article);

        // Assert
        verify(entityMapper).toArticleEntity(article);
        verify(articleRepository).save(articleEntity);
    }

    @Test
    void createArticleShouldThrowExceptionWhenRepositoryFails() {
        // Arrange
        Article article = new Article(
                null,
                "Test Brand",
                "Test Description",
                10L,
                BigDecimal.valueOf(10.0),
                new Brand(1L, "Test Brand", "Test Description"),
                List.of(new Category(1L, "Category", "Category Description"))
        );
        ArticleEntity articleEntity = new ArticleEntity();

        when(entityMapper.toArticleEntity(any(Article.class))).thenReturn(articleEntity);
        doThrow(new RuntimeException("Failed to save brand")).when(articleRepository).save(any(ArticleEntity.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> createArticleJpaAdapter.createArticle(article));

        // Verifications
        verify(entityMapper).toArticleEntity(article);
        verify(articleRepository).save(articleEntity);
    }
}
