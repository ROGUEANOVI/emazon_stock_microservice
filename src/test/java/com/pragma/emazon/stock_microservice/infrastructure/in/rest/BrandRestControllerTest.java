package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateBrandRequest;
import com.pragma.emazon.stock_microservice.application.handler.ICreateBrandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BrandRestControllerTest {
    @Mock
    private ICreateBrandHandler createBrandHandler;

    @InjectMocks
    private BrandRestController brandRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBradShouldReturnCreatedStatus() {
        // Arrange
        CreateBrandRequest request = new CreateBrandRequest("New Brand", "Category Description");

        doNothing().when(createBrandHandler).createBrand(request);

        // Act
        ResponseEntity<Void> response = brandRestController.createBrand(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(createBrandHandler).createBrand(request);
    }
}
