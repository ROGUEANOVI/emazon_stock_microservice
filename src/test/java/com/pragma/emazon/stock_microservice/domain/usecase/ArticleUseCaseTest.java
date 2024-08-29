package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.ArticleExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.*;
import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.IArticlePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IBrandPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICategoryPersistencePort;
import com.pragma.emazon.stock_microservice.domain.validation.ArticleValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static com.pragma.emazon.stock_microservice.domain.constant.ArticleValidationMessages.INVALID_ARTICLE_NAME_EMPTY_OR_BLANK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    private GenericPagination<Article> genericPagination;

    private Article article;

    private Brand brand;
    private Category category1;
    private Category category2;

    @BeforeEach
    void setUp() {

        brand = new Brand(1L, "BrandName", "BrandDescription");
        category1 = new Category(1L, "Category1", "CategoryDescription");
        category2 = new Category(2L, "Category2", "CategoryDescription");

        article = new Article();
        article.setName("ArticleName");
        article.setDescription("ArticleDescription");
        article.setQuantity(10L);
        article.setPrice(BigDecimal.valueOf(10.0));
        article.setBrand(brand);
        article.setCategories(Arrays.asList(category1, category2));

        genericPagination = new GenericPagination<>(
                List.of(article), // Content
                1, // Page Number
                10, // Page Size
                1L, // Total Elements
                1, // Total Pages
                true, // Is First
                false // Is Last
        );
    }

    @Test
    void shouldCreateArticleSuccessfully() {

        when(articlePersistencePort.existsArticleByName(article.getName())).thenReturn(false);
        when(brandPersistencePort.findBrandById(brand.getId())).thenReturn(Optional.of(brand));
        when(categoryPersistencePort.findCategoryById(category1.getId())).thenReturn(Optional.of(category1));
        when(categoryPersistencePort.findCategoryById(category2.getId())).thenReturn(Optional.of(category2));

        articleUseCase.createArticle(article);

        verify(articlePersistencePort).createArticle(article);
    }

    @Test
    void shouldThrowArticleBadRequestExceptionWhenArticleIsInvalid() {

        article.setName("");

        List<Map<String, String>> validationErrors = ArticleValidator.validate(article);

        assertFalse(validationErrors.isEmpty());

        ArticleBadRequestException exception = assertThrows(ArticleBadRequestException.class,
                () -> articleUseCase.createArticle(article));

        assertEquals(INVALID_ARTICLE_NAME_EMPTY_OR_BLANK, exception.getErrors().get(0).get("name"));
    }

    @Test
    void shouldThrowArticleAlreadyExistsExceptionWhenArticleNameExists() {

        when(articlePersistencePort.existsArticleByName(article.getName())).thenReturn(true);

        assertThrows(ArticleAlreadyExistsException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void shouldThrowBrandNotFoundExceptionWhenBrandDoesNotExist() {

        when(articlePersistencePort.existsArticleByName(article.getName())).thenReturn(false);
        when(brandPersistencePort.findBrandById(brand.getId())).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void shouldThrowCategoryNotFoundExceptionWhenCategoryDoesNotExist() {

        when(articlePersistencePort.existsArticleByName(article.getName())).thenReturn(false);
        when(brandPersistencePort.findBrandById(brand.getId())).thenReturn(Optional.of(brand));
        when(categoryPersistencePort.findCategoryById(category1.getId())).thenReturn(Optional.of(category1));
        when(categoryPersistencePort.findCategoryById(category2.getId())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> articleUseCase.createArticle(article));
    }

    @Test
    void shouldThrowDuplicateCategoryExceptionWhenArticleHasDuplicateCategories() {

        Category duplicateCategory = new Category(1L, "Category1", "CategoryDescription");
        article.setCategories(Arrays.asList(category1, duplicateCategory));

        when(articlePersistencePort.existsArticleByName(article.getName())).thenReturn(false);
        when(brandPersistencePort.findBrandById(brand.getId())).thenReturn(Optional.of(brand));
        when(categoryPersistencePort.findCategoryById(category1.getId())).thenReturn(Optional.of(category1));

        assertThrows(DuplicateCategoryException.class, () -> articleUseCase.createArticle(article));
    }


    @Test
    void listArticlesShouldReturnCustomPageWhenDataIsPresent() {

        // Arrange
        when(articlePersistencePort.listArticles(1, 10, "asc", "name")).thenReturn(genericPagination);

        // Act
        GenericPagination<Article> result = articleUseCase.listArticles(1, 10, "asc", "name");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(article, result.getContent().get(0));
        assertEquals(1, result.getPageNumber());
        assertEquals(10, result.getPageSize());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isFirstPage());

        verify(articlePersistencePort, times(1)).listArticles(1, 10, "asc", "name");
    }

    @Test
    void listArticlesShouldThrowNoDataFoundArticleExceptionWhenNoDataIsPresent() {

        // Arrange
        GenericPagination<Article> emptyGenericPagination = new GenericPagination<>(
                Collections.emptyList(), // Content
                0, // Page Number
                10, // Page Size
                0L, // Total Elements
                0, // Total Pages
                true, // Is first
                false // Is Last
        );
        when(articlePersistencePort.listArticles(1, 10, "asc", "name")).thenReturn(emptyGenericPagination);

        // Act & Assert
        NoDataFoundArticleException exception = assertThrows(NoDataFoundArticleException.class, () ->
                articleUseCase.listArticles(1, 10, "asc", "name"));

        assertEquals(ArticleExceptionMessages.NO_DATA_FOUND_ARTICLE, exception.getMessage());

        verify(articlePersistencePort, times(1)).listArticles(1, 10, "asc", "name");
    }

    @Test
    void listArticlesShouldInvokePersistencePortWithCorrectParameters() {

        // Arrange
        when(articlePersistencePort.listArticles(1, 20, "desc", "name")).thenReturn(genericPagination);

        // Act
        articleUseCase.listArticles(1, 20, "desc", "name");

        // Assert
        verify(articlePersistencePort, times(1)).listArticles(1, 20, "desc", "name");
    }
}
