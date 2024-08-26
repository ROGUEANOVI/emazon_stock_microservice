package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ExistsBrandByNameJpaAdapterTest {
    @Mock
    private IBrandRepository brandRepository;

    @InjectMocks
    private ExistsBrandByNameJpaAdapter existsBrandByNameJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsBrandByNameShouldReturnTrueWhenBrandExists() {
        // Arrange
        when(brandRepository.existsByName(anyString())).thenReturn(true);

        // Act
        Boolean result = existsBrandByNameJpaAdapter.existsBrandByName("ExistingBrand");

        // Assert
        assertTrue(result);
    }

    @Test
    void existsBrandByNameShouldReturnFalseWhenBrandDoesNotExist() {
        // Arrange
        when(brandRepository.existsByName(anyString())).thenReturn(false);

        // Act
        Boolean result = existsBrandByNameJpaAdapter.existsBrandByName("NonExistingBrand");

        // Assert
        assertFalse(result);
    }
}
