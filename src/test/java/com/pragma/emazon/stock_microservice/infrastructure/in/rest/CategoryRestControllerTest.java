package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.handler.ICategoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryRestControllerTest {

    @Mock
    private ICategoryHandler categoryHandler;

    @InjectMocks
    private CategoryRestController categoryRestController;
    private PaginatedResponse<CategoryResponse> paginatedResponse;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        CategoryResponse categoryResponse = new CategoryResponse(1L, "Electronics", "Devices and gadgets");
        List<CategoryResponse> categoryResponseList = List.of(categoryResponse);
        paginatedResponse = new PaginatedResponse<CategoryResponse>(categoryResponseList, 1, 10, 1L, 1, true, true);
    }

    @Test
    void createCategoryShouldReturnCreatedStatus() {

        // Arrange
        CreateCategoryRequest request = new CreateCategoryRequest("NewCategory", "CategoryDescription");

        doNothing().when(categoryHandler).createCategory(request);

        // Act
        ResponseEntity<Void> response = categoryRestController.createCategory(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(categoryHandler).createCategory(request);
    }

    @Test
    void listCategoriesShouldReturnPaginatedResponse() {

        // Arrange
        Integer page = 1;
        Integer size = 10;
        String direction = "ASC";

        when(categoryHandler.listCategories(page, size, direction)).thenReturn(paginatedResponse);

        // Act
        ResponseEntity<PaginatedResponse<CategoryResponse>> response = categoryRestController.listCategories(page, size, direction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paginatedResponse, response.getBody());
        verify(categoryHandler, times(1)).listCategories(page, size, direction);
    }
}