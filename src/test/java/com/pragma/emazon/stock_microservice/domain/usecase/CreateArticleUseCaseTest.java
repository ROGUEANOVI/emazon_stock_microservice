package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.exception.*;
import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateArticlePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsArticleByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IFindBrandByIdPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IFindCategoryByIdPersistencePort;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateArticleUseCaseTest {
    @Mock
    private ICreateArticlePersistencePort createArticlePersistencePort;

    @Mock
    private IExistsArticleByNamePersistencePort existsArticleByNamePersistencePort;

    @Mock
    private IFindBrandByIdPersistencePort findBrandByIdPersistencePort;

    @Mock
    private IFindCategoryByIdPersistencePort findCategoryByIdPersistencePort;

    @InjectMocks
    private CreateArticleUseCase createArticleUseCase;

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
    }

    @Test
    void shouldCreateArticleSuccessfully() {
        when(existsArticleByNamePersistencePort.existsArticleByName(article.getName())).thenReturn(false);
        when(findBrandByIdPersistencePort.findBrandById(brand.getId())).thenReturn(Optional.of(brand));
        when(findCategoryByIdPersistencePort.findCategoryById(category1.getId())).thenReturn(Optional.of(category1));
        when(findCategoryByIdPersistencePort.findCategoryById(category2.getId())).thenReturn(Optional.of(category2));

        createArticleUseCase.createArticle(article);

        verify(createArticlePersistencePort).createArticle(article);
    }

    @Test
    void shouldThrowArticleBadRequestExceptionWhenArticleIsInvalid() {
        article.setName("");

        List<Map<String, String>> validationErrors = ArticleValidator.validate(article);

        assertFalse(validationErrors.isEmpty());

        ArticleBadRequestException exception = assertThrows(ArticleBadRequestException.class,
                () -> createArticleUseCase.createArticle(article));

        assertEquals(INVALID_ARTICLE_NAME_EMPTY_OR_BLANK, exception.getErrors().get(0).get("name"));
    }

    @Test
    void shouldThrowArticleAlreadyExistsExceptionWhenArticleNameExists() {
        when(existsArticleByNamePersistencePort.existsArticleByName(article.getName())).thenReturn(true);

        assertThrows(ArticleAlreadyExistsException.class, () -> createArticleUseCase.createArticle(article));
    }

    @Test
    void shouldThrowBrandNotFoundExceptionWhenBrandDoesNotExist() {
        when(existsArticleByNamePersistencePort.existsArticleByName(article.getName())).thenReturn(false);
        when(findBrandByIdPersistencePort.findBrandById(brand.getId())).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> createArticleUseCase.createArticle(article));
    }

    @Test
    void shouldThrowCategoryNotFoundExceptionWhenCategoryDoesNotExist() {
        when(existsArticleByNamePersistencePort.existsArticleByName(article.getName())).thenReturn(false);
        when(findBrandByIdPersistencePort.findBrandById(brand.getId())).thenReturn(Optional.of(brand));
        when(findCategoryByIdPersistencePort.findCategoryById(category1.getId())).thenReturn(Optional.of(category1));
        when(findCategoryByIdPersistencePort.findCategoryById(category2.getId())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> createArticleUseCase.createArticle(article));
    }

    @Test
    void shouldThrowDuplicateCategoryExceptionWhenArticleHasDuplicateCategories() {
        Category duplicateCategory = new Category(1L, "Category1", "CategoryDescription");
        article.setCategories(Arrays.asList(category1, duplicateCategory));

        when(existsArticleByNamePersistencePort.existsArticleByName(article.getName())).thenReturn(false);
        when(findBrandByIdPersistencePort.findBrandById(brand.getId())).thenReturn(Optional.of(brand));
        when(findCategoryByIdPersistencePort.findCategoryById(category1.getId())).thenReturn(Optional.of(category1));

        assertThrows(DuplicateCategoryException.class, () -> createArticleUseCase.createArticle(article));
    }
}
