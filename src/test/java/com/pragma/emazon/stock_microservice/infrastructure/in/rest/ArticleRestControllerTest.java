package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import com.pragma.emazon.stock_microservice.application.dto.request.UpdateQuantityRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.handler.IArticleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArticleRestControllerTest {

    @Mock
    private IArticleHandler articleHandler;

    @InjectMocks
    private ArticleRestController articleRestController;
    private PaginatedResponse<ArticleResponse> paginatedResponse;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(articleRestController).build();
    }

    @Test
    void createArticleShouldReturnCreatedStatus() {

        // Arrange
        CreateArticleRequest request = new CreateArticleRequest("New Article", "Article Description", 10, BigDecimal.valueOf(1.0), 1L, List.of(1L));

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

    @Test
    @WithMockUser(roles = "WAREHOUSE_ASSISTANT")
    void updateArticleQuantityShouldReturnOkWhenValidRequest() throws Exception {

        // Arrange
        Long articleId = 1L;
        UpdateQuantityRequest request = new UpdateQuantityRequest(10);
        doNothing().when(articleHandler).updateArticleQuantity(articleId, request);

        // Act & Assert
        mockMvc.perform(put("/api/v1/articles/{id}/quantity", articleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(articleHandler).updateArticleQuantity(eq(1L), any(UpdateQuantityRequest.class));
    }
}

