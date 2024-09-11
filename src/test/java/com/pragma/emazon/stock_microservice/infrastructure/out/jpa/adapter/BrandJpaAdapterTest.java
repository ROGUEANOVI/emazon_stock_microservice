package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageBrandEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.pragma.emazon.stock_microservice.infrastructure.constant.PageMessages.PROPERTY_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class BrandJpaAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private EntityMapper entityMapper;

    @Mock
    private PageBrandEntityMapper pageBrandEntityMapper;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    private Page<BrandEntity> brandEntityPage;
    private GenericPagination<Brand> genericPagination;
    private Pageable pageable;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        Brand brand = new Brand(null, "Koaj", "description koaj");
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, PROPERTY_NAME));
        genericPagination = new GenericPagination<>(
                List.of(brand), // Content
                0, // Page Number
                10, // Page Size
                1L, // Total Elements
                1, // Total Pages
                true, // Is First
                true // Is Last
        );

        BrandEntity brandEntity = new BrandEntity(1L, "Koaj", "description koaj");
        brandEntityPage = new PageImpl<>( List.of(brandEntity), pageable, 1L );
    }

    @Test
    void createBrandShouldCallSaveOnRepository() {

        // Arrange
        Brand brand = new Brand(1L, "Test Brand", "Test Description");
        BrandEntity brandEntity = new BrandEntity();

        when(entityMapper.toBrandEntity(any(Brand.class))).thenReturn(brandEntity);

        // Act
        brandJpaAdapter.createBrand(brand);

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
        assertThrows(RuntimeException.class, () -> brandJpaAdapter.createBrand(brand));

        // Verifications
        verify(entityMapper).toBrandEntity(brand);
        verify(brandRepository).save(brandEntity);
    }


    @Test
    void listBrandsShouldReturnCustomPageWhenDataIsAvailable() {

        // Arrange
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, PROPERTY_NAME));
        when(brandRepository.findAll(pageable)).thenReturn(brandEntityPage);
        when(pageBrandEntityMapper.toGenericPaginationBrand(brandEntityPage)).thenReturn(genericPagination);

        // Act
        GenericPagination<Brand> result = brandJpaAdapter.listBrands(1, 10, "ASC");

        // Assert
        assertEquals(genericPagination, result);
        verify(brandRepository, times(1)).findAll(any(Pageable.class));
        verify(pageBrandEntityMapper, times(1)).toGenericPaginationBrand(any(Page.class));
    }

    @Test
    void listBrandsShouldUseCorrectSortingWhenDirectionIsGiven() {

        // Arrange
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(brandEntityPage);
        when(pageBrandEntityMapper.toGenericPaginationBrand(any(Page.class))).thenReturn(genericPagination);

        // Act
        GenericPagination<Brand> result = brandJpaAdapter.listBrands(1, 10, "DESC");

        // Assert
        verify(brandRepository).findAll(pageable);
        verify(pageBrandEntityMapper).toGenericPaginationBrand(brandEntityPage);
        assertEquals(genericPagination, result);
    }


    @Test
    void existsBrandByNameShouldReturnTrueWhenBrandExists() {

        // Arrange
        when(brandRepository.existsByName(anyString())).thenReturn(true);

        // Act
        Boolean result = brandJpaAdapter.existsBrandByName("ExistingBrand");

        // Assert
        assertTrue(result);
    }

    @Test
    void existsBrandByNameShouldReturnFalseWhenBrandDoesNotExist() {

        // Arrange
        when(brandRepository.existsByName(anyString())).thenReturn(false);

        // Act
        Boolean result = brandJpaAdapter.existsBrandByName("NonExistingBrand");

        // Assert
        assertFalse(result);
    }


    @Test
    void testFindBrandByIdWhenBrandExists() {

        BrandEntity expectedEntity = new BrandEntity(1L, "Test Brand", "Test Description");
        Brand expectedBrand = new Brand(1L, "Test Brand", "Test Description");

        when(brandRepository.findById(1L)).thenReturn(Optional.of(expectedEntity));
        when(entityMapper.toBrand(expectedEntity)).thenReturn(expectedBrand);

        Optional<Brand> result = brandJpaAdapter.findBrandById(1L);

        assertTrue(result.isPresent(), "El resultado debe estar presente");
        assertEquals(expectedBrand, result.get(), "La marca encontrada debe coincidir con la esperada");

        verify(brandRepository, times(1)).findById(1L);
        verify(entityMapper, times(1)).toBrand(expectedEntity);
    }

    @Test
    void testFindBrandByIdWhenBrandDoesNotExist() {

        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Brand> result = brandJpaAdapter.findBrandById(1L);

        assertFalse(result.isPresent());
        verify(brandRepository, times(1)).findById(1L);
        verify(entityMapper, never()).toBrand(any());
    }
}
