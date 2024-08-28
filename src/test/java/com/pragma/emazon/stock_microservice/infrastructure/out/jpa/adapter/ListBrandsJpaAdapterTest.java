package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Brand;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.constant.PropertyNames;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageBrandEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ListBrandsJpaAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private PageBrandEntityMapper pageBrandEntityMapper;

    @InjectMocks
    private ListBrandsJpaAdapter listBrandsJpaAdapter;

    private Page<BrandEntity> brandEntityPage;
    private GenericPagination<Brand> genericPagination;
    private Pageable pageable;

    @BeforeEach
    void setUp() {

        Brand brand = new Brand(null, "Koaj", "description koaj");
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, PropertyNames.NAME));
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
    void listBrandsShouldReturnCustomPageWhenDataIsAvailable() {

        // Arrange
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, PropertyNames.NAME));
        when(brandRepository.findAll(pageable)).thenReturn(brandEntityPage);
        when(pageBrandEntityMapper.toGenericPaginationBrand(brandEntityPage)).thenReturn(genericPagination);

        // Act
        GenericPagination<Brand> result = listBrandsJpaAdapter.listBrand(1, 10, "ASC");

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
        GenericPagination<Brand> result = listBrandsJpaAdapter.listBrand(1, 10, "DESC");

        // Assert
        verify(brandRepository).findAll(pageable);
        verify(pageBrandEntityMapper).toGenericPaginationBrand(brandEntityPage);
        assertEquals(genericPagination, result);
    }
}
