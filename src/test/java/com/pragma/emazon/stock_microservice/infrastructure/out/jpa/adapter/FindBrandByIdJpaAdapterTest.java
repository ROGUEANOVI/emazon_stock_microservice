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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class FindBrandByIdJpaAdapterTest {
    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private FindBrandByIdJpaAdapter findBrandByIdJpaAdapter;

    private BrandEntity brandEntity;
    private Brand brand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        brand = new Brand(1L, "Test Brand", "Test Description");
        brandEntity = new BrandEntity(1L, "Test Brand", "Test Description");
    }

    @Test
    void testFindBrandById_WhenBrandExists() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brandEntity));
        when(entityMapper.toBrand(brandEntity)).thenReturn(brand);

        Optional<Brand> result = findBrandByIdJpaAdapter.findBrandById(1L);

        assertEquals(Optional.of(brand), result);
        verify(brandRepository, times(1)).findById(1L);
        verify(entityMapper, times(1)).toBrand(brandEntity);
    }

    @Test
    void testFindBrandById_WhenBrandDoesNotExist() {
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Brand> result = findBrandByIdJpaAdapter.findBrandById(1L);

        assertFalse(result.isPresent());
        verify(brandRepository, times(1)).findById(1L);
        verify(entityMapper, never()).toBrand(any());
    }
}