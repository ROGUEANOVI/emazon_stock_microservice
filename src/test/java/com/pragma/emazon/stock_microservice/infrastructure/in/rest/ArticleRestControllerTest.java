package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.handler.IArticleHandler;
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
    private IArticleHandler articleHandler;

    @InjectMocks
    private ArticleRestController articleRestController;
    private PaginatedResponse<ArticleResponse> paginatedResponse;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createArticleShouldReturnCreatedStatus() {

        // Arrange
        CreateArticleRequest request = new CreateArticleRequest("New Article", "Article Description", 1L, BigDecimal.valueOf(1.0), 1L, List.of(1L));

        doNothing().when(articleHandler).createArticle(request);

        // Act
        ResponseEntity<Void> response = articleRestController.createArticle(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(articleHandler).createArticle(request);
    }

    @Test
    void listArticlesShouldReturnPaginatedResponse() {

        // Arrange
        Integer page = 1;
        Integer size = 10;
        String direction = "ASC";
        String sortBy = "article";

        when(articleHandler.listArticles(page, size, direction, sortBy)).thenReturn(paginatedResponse);

        // Act
        ResponseEntity<PaginatedResponse<ArticleResponse>> response = articleRestController.listArticles(page, size, direction, sortBy);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paginatedResponse, response.getBody());
        verify(articleHandler, times(1)).listArticles(page, size, direction, sortBy);
    }
}

