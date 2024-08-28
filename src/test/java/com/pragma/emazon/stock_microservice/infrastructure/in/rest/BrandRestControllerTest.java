package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateBrandRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.BrandResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.handler.ICreateBrandHandler;
import com.pragma.emazon.stock_microservice.application.handler.ListBrandsHandler;
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
    @Mock
    private ListBrandsHandler listBrandsHandler;

    @InjectMocks
    private BrandRestController brandRestController;
    private PaginatedResponse<BrandResponse> paginatedResponse;

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

    @Test
    void listBrandsShouldReturnPaginatedResponse() {

        // Arrange
        Integer page = 1;
        Integer size = 10;
        String direction = "ASC";

        when(listBrandsHandler.listBrands(page, size, direction)).thenReturn(paginatedResponse);

        // Act
        ResponseEntity<PaginatedResponse<BrandResponse>> response = brandRestController.listBrands(page, size, direction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paginatedResponse, response.getBody());
        verify(listBrandsHandler, times(1)).listBrands(page, size, direction);
    }
}
