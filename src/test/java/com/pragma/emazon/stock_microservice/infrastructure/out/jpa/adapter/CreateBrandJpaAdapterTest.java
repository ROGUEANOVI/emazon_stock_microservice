package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class CreateBrandJpaAdapterTest {
    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private CreateBrandJpaAdapter createBrandJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBrandShouldCallSaveOnRepository() {
        // Arrange
        Brand brand = new Brand(1L, "Test Brand", "Test Description");
        BrandEntity brandEntity = new BrandEntity();

        when(entityMapper.toBrandEntity(any(Brand.class))).thenReturn(brandEntity);

        // Act
        createBrandJpaAdapter.createBrand(brand);

        // Assert
        verify(entityMapper).toBrandEntity(brand);
        verify(brandRepository).save(brandEntity);
    }

    @Test
    void createBrandShouldThrowExceptionWhenRepositoryFails() {
        // Arrange
        Brand brand = new Brand(null, null, "");
        BrandEntity brandEntity = new BrandEntity();

        when(entityMapper.toBrandEntity(any(Brand.class))).thenReturn(brandEntity);
        doThrow(new RuntimeException("Failed to save brand")).when(brandRepository).save(any(BrandEntity.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> createBrandJpaAdapter.createBrand(brand));

        // Verifications
        verify(entityMapper).toBrandEntity(brand);
        verify(brandRepository).save(brandEntity);
    }
}
