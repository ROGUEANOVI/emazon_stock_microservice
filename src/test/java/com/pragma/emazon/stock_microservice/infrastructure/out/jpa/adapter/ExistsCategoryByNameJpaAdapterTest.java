package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ExistsCategoryByNameJpaAdapterTest {
    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private ExistsCategoryByNameJpaAdapter existsCategoryByNameJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsCategoryByNameShouldReturnTrueWhenCategoryExists() {
        // Arrange
        when(categoryRepository.existsByName(anyString())).thenReturn(true);

        // Act
        boolean result = existsCategoryByNameJpaAdapter.existsCategoryByName("ExistingCategory");

        // Assert
        assertTrue(result);
    }

    @Test
    void existsCategoryByNameShouldReturnFalseWhenCategoryDoesNotExist() {
        // Arrange
        when(categoryRepository.existsByName(anyString())).thenReturn(false);

        // Act
        boolean result = existsCategoryByNameJpaAdapter.existsCategoryByName("NonExistingCategory");

        // Assert
        assertFalse(result);
    }
}
