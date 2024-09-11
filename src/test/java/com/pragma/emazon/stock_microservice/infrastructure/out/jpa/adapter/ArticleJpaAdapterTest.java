package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.ArticleEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageArticleEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;

import static com.pragma.emazon.stock_microservice.infrastructure.constant.PageMessages.PROPERTY_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ArticleJpaAdapterTest {

    @Mock
    private IArticleRepository articleRepository;

    @Mock
    private EntityMapper entityMapper;

    @Mock
    private PageArticleEntityMapper pageArticleEntityMapper;

    @InjectMocks
    private ArticleJpaAdapter articleJpaAdapter;

    private Page<ArticleEntity> articleEntityPage;
    private GenericPagination<Article> genericPagination;
    private Pageable pageable;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        Article article = new Article(null, "Article 45", "description ae", 10L, BigDecimal.valueOf(10.0), new Brand(1L, "Koaj", "description koaj"), List.of());
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, PROPERTY_NAME));
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
        articleJpaAdapter.createArticle(article);

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
        assertThrows(RuntimeException.class, () -> articleJpaAdapter.createArticle(article));

        // Verifications
        verify(entityMapper).toArticleEntity(article);
        verify(articleRepository).save(articleEntity);
    }


    @Test
    void listBrandsShouldReturnCustomPageWhenDataIsAvailable() {

        // Arrange
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, PROPERTY_NAME));
        when(articleRepository.findAll(pageable)).thenReturn(articleEntityPage);
        when(pageArticleEntityMapper.toGenericPaginationArticle(articleEntityPage)).thenReturn(genericPagination);

        // Act
        GenericPagination<Article> result = articleJpaAdapter.listArticles(1, 10, "ASC", "name");

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
        GenericPagination<Article> result = articleJpaAdapter.listArticles(1, 10, "DESC", "name");

        // Assert
        verify(articleRepository).findAll(pageable);
        verify(pageArticleEntityMapper).toGenericPaginationArticle(articleEntityPage);
        assertEquals(genericPagination, result);
    }


    @Test
    void existsArticleByNameShouldReturnTrueWhenArticleExists() {

        // Arrange
        when(articleRepository.existsByName(anyString())).thenReturn(true);

        // Act
        Boolean result = articleJpaAdapter.existsArticleByName("ExistingArticle");

        // Assert
        assertTrue(result);
    }

    @Test
    void existsArticleByNameShouldReturnFalseWhenArticleDoesNotExist() {

        // Arrange
        when(articleRepository.existsByName(anyString())).thenReturn(false);

        // Act
        Boolean result = articleJpaAdapter.existsArticleByName("NonExistingArticle");

        // Assert
        assertFalse(result);
    }
}
