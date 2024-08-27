package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import com.pragma.emazon.stock_microservice.application.handler.ICreateArticleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleRestControllerTest {
    @Mock
    private ICreateArticleHandler createArticleHandler;

    @InjectMocks
    private ArticleRestController articleRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createArticleShouldReturnCreatedStatus() {
        // Arrange
        CreateArticleRequest request = new CreateArticleRequest("New Article", "Article Description", 1L, BigDecimal.valueOf(1.0), 1L, List.of(1L));

        doNothing().when(createArticleHandler).createArticle(request);

        // Act
        ResponseEntity<Void> response = articleRestController.createArticle(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(createArticleHandler).createArticle(request);
    }
}

