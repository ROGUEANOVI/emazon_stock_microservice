package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.application.handler.ICreateCategoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class CategoryRestControllerTest {
    @Mock
    private ICreateCategoryHandler createCategoryHandler;

    @InjectMocks
    private CategoryRestController categoryRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
}