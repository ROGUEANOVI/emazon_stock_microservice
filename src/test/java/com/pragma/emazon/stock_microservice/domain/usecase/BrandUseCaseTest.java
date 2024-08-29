package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.BrandExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.BrandAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundBrandException;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    private Brand brand;


    private GenericPagination<Brand> genericPagination;

    @BeforeEach
    void setUp() {

        brand = new Brand(1L, "Koaj", "Prendas de vestir para dama y caballeros");
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
    void shouldCreateBrandSuccessfully() {

        // Arrange
        when(brandPersistencePort.existsBrandByName(brand.getName())).thenReturn(false);

        // Act
        brandUseCase.createBrand(brand);

        // Assert
        verify(brandPersistencePort, times(1)).createBrand(any(Brand.class));
    }

    @Test
    void shouldThrowBrandAlreadyExistsException() {

        // Arrange
        when(brandPersistencePort.existsBrandByName(brand.getName())).thenReturn(true);

        // Act and Assert
        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCase.createBrand(brand));
    }

    @Test
    void listBrandsShouldReturnCustomPageWhenDataIsPresent() {

        // Arrange
        when(brandPersistencePort.listBrands(1, 10, "asc")).thenReturn(genericPagination);

        // Act
        GenericPagination<Brand> result = brandUseCase.listBrands(1, 10, "asc");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(brand, result.getContent().get(0));
        assertEquals(1, result.getPageNumber());
        assertEquals(10, result.getPageSize());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isFirstPage());

        verify(brandPersistencePort, times(1)).listBrands(1, 10, "asc");
    }

    @Test
    void listBrandsShouldThrowNoDataFoundBrandExceptionWhenNoDataIsPresent() {

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
        when(brandPersistencePort.listBrands(1, 10, "asc")).thenReturn(emptyGenericPagination);

        // Act & Assert
        NoDataFoundBrandException exception = assertThrows(NoDataFoundBrandException.class, () ->
                brandUseCase.listBrands(1, 10, "asc"));

        assertEquals(BrandExceptionMessages.NO_DATA_FOUND_BRAND, exception.getMessage());

        verify(brandPersistencePort, times(1)).listBrands(1, 10, "asc");
    }

    @Test
    void listBrandsShouldInvokePersistencePortWithCorrectParameters() {

        // Arrange
        when(brandPersistencePort.listBrands(1, 20, "desc")).thenReturn(genericPagination);

        // Act
        brandUseCase.listBrands(1, 20, "desc");

        // Assert
        verify(brandPersistencePort, times(1)).listBrands(1, 20, "desc");
    }
}
