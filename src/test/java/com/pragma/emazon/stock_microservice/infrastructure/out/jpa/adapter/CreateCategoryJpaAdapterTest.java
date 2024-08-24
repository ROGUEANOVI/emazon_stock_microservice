package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateCategoryJpaAdapterTest {
    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CreateCategoryJpaAdapter createCategoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategoryShouldCallSaveOnRepository() {
        // Arrange
        Category category = new Category(1L, "TestCategory", "TestDescription");
        CategoryEntity categoryEntity = new CategoryEntity();

        when(categoryEntityMapper.toEntity(any(Category.class))).thenReturn(categoryEntity);

        // Act
        createCategoryJpaAdapter.createCategory(category);

        // Assert
        verify(categoryEntityMapper).toEntity(category);
        verify(categoryRepository).save(categoryEntity);
    }

    @Test
    void createCategoryShouldThrowExceptionWhenRepositoryFails() {
        // Arrange
        Category category = new Category(null, null, "");
        CategoryEntity categoryEntity = new CategoryEntity();

        when(categoryEntityMapper.toEntity(any(Category.class))).thenReturn(categoryEntity);
        doThrow(new RuntimeException("Failed to save category")).when(categoryRepository).save(any(CategoryEntity.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> createCategoryJpaAdapter.createCategory(category));

        // Verifications
        verify(categoryEntityMapper).toEntity(category);
        verify(categoryRepository).save(categoryEntity);
    }
}
