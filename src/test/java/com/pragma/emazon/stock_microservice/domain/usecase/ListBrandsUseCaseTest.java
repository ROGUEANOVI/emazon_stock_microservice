package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.BrandExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundBrandException;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListBrandsPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListBrandsUseCaseTest {
    @Mock
    private IListBrandsPersistencePort listBrandsPersistencePort;

    @InjectMocks
    private ListBrandsUseCase listBrandsUseCase;

    private GenericPagination<Brand> genericPagination;

    private Brand brand;

    @BeforeEach
    void setUp() {
        brand = new Brand(null, "Koaj", "Brands of Koaj");
        genericPagination = new GenericPagination<>(
                List.of(brand), // Content
                1, // Page Number
                10, // Page Size
                1L, // Total Elements
                1, // Total Pages
                true, // Is First
                false // Is Last
        );
    }

    @Test
    void listBrandsShouldReturnCustomPageWhenDataIsPresent() {
        // Arrange
        when(listBrandsPersistencePort.listBrand(1, 10, "asc")).thenReturn(genericPagination);

        // Act
        GenericPagination<Brand> result = listBrandsUseCase.listBrands(1, 10, "asc");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(brand, result.getContent().get(0));
        assertEquals(1, result.getPageNumber());
        assertEquals(10, result.getPageSize());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isFirstPage());

        verify(listBrandsPersistencePort, times(1)).listBrand(1, 10, "asc");
    }

    @Test
    void listBrandShouldThrowNoDataFoundBrandExceptionWhenNoDataIsPresent() {
        // Arrange
        GenericPagination<Brand> emptyGenericPagination = new GenericPagination<>(
                Collections.emptyList(), // Content
                0, // Page Number
                10, // Page Size
                0L, // Total Elements
                0, // Total Pages
                true, // Is first
                false // Is Last
        );
        when(listBrandsPersistencePort.listBrand(1, 10, "asc")).thenReturn(emptyGenericPagination);

        // Act & Assert
        NoDataFoundBrandException exception = assertThrows(NoDataFoundBrandException.class, () ->
                listBrandsUseCase.listBrands(1, 10, "asc"));

        assertEquals(BrandExceptionMessages.NO_DATA_FOUND_BRAND, exception.getMessage());

        verify(listBrandsPersistencePort, times(1)).listBrand(1, 10, "asc");
    }

    @Test
    void listBrandsShouldInvokePersistencePortWithCorrectParameters() {
        // Arrange
        when(listBrandsPersistencePort.listBrand(1, 20, "desc")).thenReturn(genericPagination);

        // Act
        listBrandsUseCase.listBrands(1, 20, "desc");

        // Assert
        verify(listBrandsPersistencePort, times(1)).listBrand(1, 20, "desc");
    }
}
