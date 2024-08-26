package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.handler.ICreateCategoryHandler;
import com.pragma.emazon.stock_microservice.application.handler.ListCategoriesHandler;
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
    private ICreateCategoryHandler createCategoryHandler;

    @Mock
    private ListCategoriesHandler listCategoriesHandler;

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

        doNothing().when(createCategoryHandler).createCategory(request);

        // Act
        ResponseEntity<Void> response = categoryRestController.createCategory(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(createCategoryHandler).createCategory(request);
    }

    @Test
    void listCategoriesShouldReturnPaginatedResponse() {
        // Arrange
        Integer page = 1;
        Integer size = 10;
        String direction = "ASC";

        when(listCategoriesHandler.listCategories(page, size, direction)).thenReturn(paginatedResponse);

        // Act
        ResponseEntity<PaginatedResponse<CategoryResponse>> response = categoryRestController.listCategories(page, size, direction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paginatedResponse, response.getBody());
        verify(listCategoriesHandler, times(1)).listCategories(page, size, direction);
    }
}