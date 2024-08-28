package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ExistsArticleByNameJpaAdapterTest {

    @Mock
    private IArticleRepository articleRepository;

    @InjectMocks
    private ExistsArticleByNameJpaAdapter existsArticleByNameJpaAdapter;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsArticleByNameShouldReturnTrueWhenArticleExists() {

        // Arrange
        when(articleRepository.existsByName(anyString())).thenReturn(true);

        // Act
        Boolean result = existsArticleByNameJpaAdapter.existsArticleByName("ExistingArticle");

        // Assert
        assertTrue(result);
    }

    @Test
    void existsArticleByNameShouldReturnFalseWhenArticleDoesNotExist() {

        // Arrange
        when(articleRepository.existsByName(anyString())).thenReturn(false);

        // Act
        Boolean result = existsArticleByNameJpaAdapter.existsArticleByName("NonExistingArticle");

        // Assert
        assertFalse(result);
    }
}
