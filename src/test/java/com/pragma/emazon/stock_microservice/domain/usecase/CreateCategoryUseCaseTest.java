package com.pragma.emazon.stock_microservice.domain.usecase;

import com.pragma.emazon.stock_microservice.domain.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.stock_microservice.domain.exception.CategoryBadRequestException;
import com.pragma.emazon.stock_microservice.domain.model.Category;
import com.pragma.emazon.stock_microservice.domain.port.spi.ICreateCategoryPersistencePort;
import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsCategoryByNamePersistencePort;
import com.pragma.emazon.stock_microservice.domain.validation.CategoryValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseTest {

    @Mock
    private ICreateCategoryPersistencePort createCategoryPersistencePort;

    @Mock
    private IExistsCategoryByNamePersistencePort existsCategoryByNamePersistencePort;

    @InjectMocks
    private CreateCategoryUseCase createCategoryUseCase;

    private Category category;


    @BeforeEach
    void setUp() {

        category = new Category(1L, "Skin care", "Articles to care for and beautify the skin");
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
                createCategoryUseCase.createCategory(invalidCategory);
            });
            assertEquals(errors, thrownException.getErrors());
            verify(createCategoryPersistencePort, never()).createCategory(any(Category.class));
        }
    }

    @Test
    void shouldCreateCategorySuccessfully() {

        // Arrange
        when(existsCategoryByNamePersistencePort.existsCategoryByName(category.getName())).thenReturn(false);

        // Act
        createCategoryUseCase.createCategory(category);

        // Assert
        verify(createCategoryPersistencePort, times(1)).createCategory(any(Category.class));
    }

    @Test
    void shouldThrowCategoryAlreadyExistsException() {

        // Arrange
        when(existsCategoryByNamePersistencePort.existsCategoryByName(category.getName())).thenReturn(true);

        // Act and Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> createCategoryUseCase.createCategory(category));
    }
}

