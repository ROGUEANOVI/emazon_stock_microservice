package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.constant.PropertyNames;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageCategoryEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
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


@ExtendWith(MockitoExtension.class)
class ListCategoriesJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private PageCategoryEntityMapper pageCategoryEntityMapper;

    @InjectMocks
    private ListCategoriesJpaAdapter listCategoriesJpaAdapter;

    private Page<CategoryEntity> categoryEntityPage;
    private GenericPagination<Category> genericPagination;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        Category category = new Category(null, "Electronics", "Gadgets and devices");
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, PropertyNames.NAME));
        genericPagination = new GenericPagination<>(
                List.of(category), // Content
                0, // Page Number
                10, // Page Size
                1L, // Total Elements
                1, // Total Pages
                true, // Is First
                true // Is Last
        );

        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics", "Gadgets and devices");
        categoryEntityPage = new PageImpl<>( List.of(categoryEntity), pageable, 1L );
    }

    @Test
    void listCategoriesShouldReturnCustomPageWhenDataIsAvailable() {
        // Arrange
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, PropertyNames.NAME));
        when(categoryRepository.findAll(pageable)).thenReturn(categoryEntityPage);
        when(pageCategoryEntityMapper.toGenericPaginationCategory(categoryEntityPage)).thenReturn(genericPagination);

        // Act
        GenericPagination<Category> result = listCategoriesJpaAdapter.listCategories(1, 10, "ASC");

        // Assert
        assertEquals(genericPagination, result);
        verify(categoryRepository, times(1)).findAll(any(Pageable.class));
        verify(pageCategoryEntityMapper, times(1)).toGenericPaginationCategory(any(Page.class));
    }

    @Test
    void listCategoriesShouldUseCorrectSortingWhenDirectionIsGiven() {
        // Arrange
        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(categoryEntityPage);
        when(pageCategoryEntityMapper.toGenericPaginationCategory(any(Page.class))).thenReturn(genericPagination);

        // Act
        GenericPagination<Category> result = listCategoriesJpaAdapter.listCategories(1, 10, "DESC");

        // Assert
        verify(categoryRepository).findAll(pageable);
        verify(pageCategoryEntityMapper).toGenericPaginationCategory(categoryEntityPage);
        assertEquals(genericPagination, result);
    }
}
