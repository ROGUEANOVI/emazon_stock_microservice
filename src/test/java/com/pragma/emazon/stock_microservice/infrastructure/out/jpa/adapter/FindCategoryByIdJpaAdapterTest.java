package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class FindCategoryByIdJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private FindCategoryByIdJpaAdapter findCategoryByIdJpaAdapter;

    private CategoryEntity categoryEntity;
    private Category category;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        category = new Category(1L, "Test Category", "Test Description");
        categoryEntity = new CategoryEntity(1L, "Test Category", "Test Description");
    }

    @Test
    void testFindCategoryById_WhenCategoryExists() {

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(entityMapper.toCategory(categoryEntity)).thenReturn(category);

        Optional<Category> result = findCategoryByIdJpaAdapter.findCategoryById(1L);

        assertEquals(Optional.of(category), result);
        verify(categoryRepository, times(1)).findById(1L);
        verify(entityMapper, times(1)).toCategory(categoryEntity);
    }

    @Test
    void testFindCategoryById_WhenCategoryDoesNotExist() {

        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Category> result = findCategoryByIdJpaAdapter.findCategoryById(1L);

        assertFalse(result.isPresent());
        verify(categoryRepository, times(1)).findById(1L);
        verify(entityMapper, never()).toCategory(any());
    }
}
