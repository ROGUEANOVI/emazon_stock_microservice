package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.constant.CategoryExceptionMessages;
import com.pragma.emazon.stock_microservice.domain.exception.NoDataFoundCategoryException;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.model.GenericPagination;
import com.pragma.emazon.stock_microservice.domain.port.spi.IListCategoriesPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListCategoriesUseCaseTest {

    @Mock
    private IListCategoriesPersistencePort listCategoriesPersistencePort;

    @InjectMocks
    private ListCategoriesUseCase listCategoriesUseCase;

    private GenericPagination<Category> genericPagination;

    private Category category;

    @BeforeEach
    void setUp() {

        category = new Category(null, "Electronics", "Gadgets and devices");
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
    void listCategoriesShouldReturnCustomPageWhenDataIsPresent() {

        // Arrange
        when(listCategoriesPersistencePort.listCategories(1, 10, "asc")).thenReturn(genericPagination);

        // Act
        GenericPagination<Category> result = listCategoriesUseCase.listCategories(1, 10, "asc");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(category, result.getContent().get(0));
        assertEquals(1, result.getPageNumber());
        assertEquals(10, result.getPageSize());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isFirstPage());

        verify(listCategoriesPersistencePort, times(1)).listCategories(1, 10, "asc");
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
        when(listCategoriesPersistencePort.listCategories(1, 10, "asc")).thenReturn(emptyGenericPagination);

        // Act & Assert
        NoDataFoundCategoryException exception = assertThrows(NoDataFoundCategoryException.class, () ->
                listCategoriesUseCase.listCategories(1, 10, "asc"));

        assertEquals(CategoryExceptionMessages.NO_DATA_FOUND_CATEGORY, exception.getMessage());

        verify(listCategoriesPersistencePort, times(1)).listCategories(1, 10, "asc");
    }

    @Test
    void listCategoriesShouldInvokePersistencePortWithCorrectParameters() {

        // Arrange
        when(listCategoriesPersistencePort.listCategories(1, 20, "desc")).thenReturn(genericPagination);

        // Act
        listCategoriesUseCase.listCategories(1, 20, "desc");

        // Assert
        verify(listCategoriesPersistencePort, times(1)).listCategories(1, 20, "desc");
    }
}
