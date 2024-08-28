package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.ArticleExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundArticleException;
import com.pragma.emazon.stock_microservice.domain.model.Article;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListArticlesPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListArticlesUseCaseTest {

    @Mock
    private IListArticlesPersistencePort listArticlesPersistencePort;

    @InjectMocks
    private ListArticlesUseCase listArticlesUseCase;

    private GenericPagination<Article> genericPagination;

    private Article article;

    @BeforeEach
    void setUp() {

        article = new Article(
                null,
                "Article 1",
                "description",
                10L,
                BigDecimal.valueOf(10.0),
                new Brand(null, "Brand 1", "description"),
                new ArrayList<>(List.of(new Category(null, "Category 1", "description")))
        );
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
    void listArticlesShouldReturnCustomPageWhenDataIsPresent() {

        // Arrange
        when(listArticlesPersistencePort.listArticles(1, 10, "asc", "name")).thenReturn(genericPagination);

        // Act
        GenericPagination<Article> result = listArticlesUseCase.listArticles(1, 10, "asc", "name");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(article, result.getContent().get(0));
        assertEquals(1, result.getPageNumber());
        assertEquals(10, result.getPageSize());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isFirstPage());

        verify(listArticlesPersistencePort, times(1)).listArticles(1, 10, "asc", "name");
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
        when(listArticlesPersistencePort.listArticles(1, 10, "asc", "name")).thenReturn(emptyGenericPagination);

        // Act & Assert
        NoDataFoundArticleException exception = assertThrows(NoDataFoundArticleException.class, () ->
                listArticlesUseCase.listArticles(1, 10, "asc", "name"));

        assertEquals(ArticleExceptionMessages.NO_DATA_FOUND_ARTICLE, exception.getMessage());

        verify(listArticlesPersistencePort, times(1)).listArticles(1, 10, "asc", "name");
    }

    @Test
    void listArticlesShouldInvokePersistencePortWithCorrectParameters() {

        // Arrange
        when(listArticlesPersistencePort.listArticles(1, 20, "desc", "name")).thenReturn(genericPagination);

        // Act
        listArticlesUseCase.listArticles(1, 20, "desc", "name");

        // Assert
        verify(listArticlesPersistencePort, times(1)).listArticles(1, 20, "desc", "name");
    }
}
