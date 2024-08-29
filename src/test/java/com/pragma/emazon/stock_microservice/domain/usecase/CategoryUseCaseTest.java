package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.CategoryExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.CategoryBadRequestException;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundCategoryException;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICategoryPersistencePort;
import com.pragma.emazon.stock_microservice.domain.validation.CategoryValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    private Category category;

    private GenericPagination<Category> genericPagination;

    @BeforeEach
    void setUp() {

        category = new Category(1L, "Skin care", "Articles to care for and beautify the skin");
        genericPagination = new GenericPagination<>(
                List.of(category), // Content
                1, // Page Number
                10, // Page Size
                1L, // Total Elements
                1, // Total Pages
                true, // Is First
                false // Is Last
        );
    }

    @Test
    void shouldThrowExceptionWhenCategoryIsInvalid() {

        // Arrange
        Category invalidCategory = new Category(null, "", "");
        List<Map<String, String>> errors = List.of(Map.of("name", "Category name cannot be blank"));

        try (var mockStatic = mockStatic(CategoryValidator.class)) {
            mockStatic.when(() -> CategoryValidator.validate(invalidCategory)).thenReturn(errors);

            // Act and Assert
            CategoryBadRequestException thrownException = assertThrows(CategoryBadRequestException.class, () -> {
                categoryUseCase.createCategory(invalidCategory);
            });
            assertEquals(errors, thrownException.getErrors());
            verify(categoryPersistencePort, never()).createCategory(any(Category.class));
        }
    }

    @Test
    void shouldCreateCategorySuccessfully() {

        // Arrange
        when(categoryPersistencePort.existsCategoryByName(category.getName())).thenReturn(false);

        // Act
        categoryUseCase.createCategory(category);

        // Assert
        verify(categoryPersistencePort, times(1)).createCategory(any(Category.class));
    }

    @Test
    void shouldThrowCategoryAlreadyExistsException() {

        // Arrange
        when(categoryPersistencePort.existsCategoryByName(category.getName())).thenReturn(true);

        // Act and Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.createCategory(category));
    }


    @Test
    void listCategoriesShouldReturnCustomPageWhenDataIsPresent() {

        // Arrange
        when(categoryPersistencePort.listCategories(1, 10, "asc")).thenReturn(genericPagination);

        // Act
        GenericPagination<Category> result = categoryUseCase.listCategories(1, 10, "asc");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(category, result.getContent().get(0));
        assertEquals(1, result.getPageNumber());
        assertEquals(10, result.getPageSize());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isFirstPage());

        verify(categoryPersistencePort, times(1)).listCategories(1, 10, "asc");
    }

    @Test
    void listCategoriesShouldThrowNoDataFoundCategoryExceptionWhenNoDataIsPresent() {

        // Arrange
        GenericPagination<Category> emptyGenericPagination = new GenericPagination<>(
                Collections.emptyList(), // Content
                0, // Page Number
                10, // Page Size
                0L, // Total Elements
                0, // Total Pages
                true, // Is first
                false // Is Last
        );
        when(categoryPersistencePort.listCategories(1, 10, "asc")).thenReturn(emptyGenericPagination);

        // Act & Assert
        NoDataFoundCategoryException exception = assertThrows(NoDataFoundCategoryException.class, () ->
                categoryUseCase.listCategories(1, 10, "asc"));

        assertEquals(CategoryExceptionMessages.NO_DATA_FOUND_CATEGORY, exception.getMessage());

        verify(categoryPersistencePort, times(1)).listCategories(1, 10, "asc");
    }

    @Test
    void listCategoriesShouldInvokePersistencePortWithCorrectParameters() {

        // Arrange
        when(categoryPersistencePort.listCategories(1, 20, "desc")).thenReturn(genericPagination);

        // Act
        categoryUseCase.listCategories(1, 20, "desc");

        // Assert
        verify(categoryPersistencePort, times(1)).listCategories(1, 20, "desc");
    }
}

