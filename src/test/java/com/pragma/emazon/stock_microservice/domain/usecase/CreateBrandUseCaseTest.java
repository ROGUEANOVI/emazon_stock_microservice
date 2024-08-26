package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.exception.BrandAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.BrandBadRequestException;
import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateBrandPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsBrandByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.validation.BrandValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateBrandUseCaseTest {
    @Mock
    private ICreateBrandPersistencePort createBrandPersistencePort;

    @Mock
    private IExistsBrandByNamePersistencePort existsBrandByNamePersistencePort;

    @InjectMocks
    private CreateBrandUseCase createBrandUseCase;

    private Brand brand;


    @BeforeEach
    void setUp() {
        brand = new Brand(1L, "Koaj", "Prendas de vestir para dama y caballeros");
    }

    @Test
    void shouldThrowExceptionWhenBrandIsInvalid() {
        // Arrange
        Brand invalidBrand = new Brand(null, "", "");
        List<Map<String, String>> errors = List.of(Map.of("name", "Brand name cannot be blank"));

        try (var mockStatic = mockStatic(BrandValidator.class)) {
            mockStatic.when(() -> BrandValidator.validate(invalidBrand)).thenReturn(errors);

            // Act and Assert
            BrandBadRequestException thrownException = assertThrows(BrandBadRequestException.class, () -> {
                createBrandUseCase.createBrand(invalidBrand);
            });
            assertEquals(errors, thrownException.getErrors());
            verify(createBrandPersistencePort, never()).createBrand(any(Brand.class));
        }
    }

    @Test
    void shouldCreateBrandSuccessfully() {
        // Arrange
        when(existsBrandByNamePersistencePort.existsBrandByName(brand.getName())).thenReturn(false);

        // Act
        createBrandUseCase.createBrand(brand);

        // Assert
        verify(createBrandPersistencePort, times(1)).createBrand(any(Brand.class));
    }

    @Test
    void shouldThrowBrandAlreadyExistsException() {
        // Arrange
        when(existsBrandByNamePersistencePort.existsBrandByName(brand.getName())).thenReturn(true);

        // Act and Assert
        assertThrows(BrandAlreadyExistsException.class, () -> createBrandUseCase.createBrand(brand));
    }
}
