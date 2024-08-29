package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.infrastructure.constant.PropertyNames;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.EntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.mapper.PageCategoryEntityMapper;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private EntityMapper entityMapper;

    @Mock
    private PageCategoryEntityMapper pageCategoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    private Page<CategoryEntity> categoryEntityPage;
    private GenericPagination<Category> genericPagination;
    private Pageable pageable;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

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
    void createCategoryShouldCallSaveOnRepository() {

        // Arrange
        Category category = new Category(1L, "TestCategory", "TestDescription");
        CategoryEntity categoryEntity = new CategoryEntity();

        when(entityMapper.toCategoryEntity(any(Category.class))).thenReturn(categoryEntity);

        // Act
        categoryJpaAdapter.createCategory(category);

        // Assert
        verify(entityMapper).toCategoryEntity(category);
        verify(categoryRepository).save(categoryEntity);
    }

    @Test
    void createCategoryShouldThrowExceptionWhenRepositoryFails() {

        // Arrange
        Category category = new Category(null, null, "");
        CategoryEntity categoryEntity = new CategoryEntity();

        when(entityMapper.toCategoryEntity(any(Category.class))).thenReturn(categoryEntity);
        doThrow(new RuntimeException("Failed to save category")).when(categoryRepository).save(any(CategoryEntity.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> categoryJpaAdapter.createCategory(category));

        // Verifications
        verify(entityMapper).toCategoryEntity(category);
        verify(categoryRepository).save(categoryEntity);
    }


    @Test
    void listCategoriesShouldReturnCustomPageWhenDataIsAvailable() {

        // Arrange
        pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, PropertyNames.NAME));
        when(categoryRepository.findAll(pageable)).thenReturn(categoryEntityPage);
        when(pageCategoryEntityMapper.toGenericPaginationCategory(categoryEntityPage)).thenReturn(genericPagination);

        // Act
        GenericPagination<Category> result = categoryJpaAdapter.listCategories(1, 10, "ASC");

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
        GenericPagination<Category> result = categoryJpaAdapter.listCategories(1, 10, "DESC");

        // Assert
        verify(categoryRepository).findAll(pageable);
        verify(pageCategoryEntityMapper).toGenericPaginationCategory(categoryEntityPage);
        assertEquals(genericPagination, result);
    }

    @Test
    void existsCategoryByNameShouldReturnTrueWhenCategoryExists() {

        // Arrange
        when(categoryRepository.existsByName(anyString())).thenReturn(true);

        // Act
        Boolean result = categoryJpaAdapter.existsCategoryByName("ExistingCategory");

        // Assert
        assertTrue(result);
    }

    @Test
    void existsCategoryByNameShouldReturnFalseWhenCategoryDoesNotExist() {

        // Arrange
        when(categoryRepository.existsByName(anyString())).thenReturn(false);

        // Act
        Boolean result = categoryJpaAdapter.existsCategoryByName("NonExistingCategory");

        // Assert
        assertFalse(result);
    }


    @Test
    void testFindCategoryByIdWhenCategoryExists() {
        CategoryEntity expectedEntity = new CategoryEntity(1L, "Test Category", "Test Description");
        Category expectedCategory = new Category(1L, "Test Category", "Test Description");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(expectedEntity));
        when(entityMapper.toCategory(expectedEntity)).thenReturn(expectedCategory);

        Optional<Category> result = categoryJpaAdapter.findCategoryById(1L);

        assertTrue(result.isPresent(), "El resultado debe estar presente");
        assertEquals(expectedCategory, result.get(), "La categoría encontrada debe coincidir con la esperada");

        verify(categoryRepository, times(1)).findById(1L);
        verify(entityMapper, times(1)).toCategory(expectedEntity);
    }

    @Test
    void testFindCategoryByIdWhenCategoryDoesNotExist() {

        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Category> result = categoryJpaAdapter.findCategoryById(1L);

        assertFalse(result.isPresent());
        verify(categoryRepository, times(1)).findById(1L);
        verify(entityMapper, never()).toCategory(any());
    }
}
