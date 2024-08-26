package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.CategoryResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.handler.ICreateCategoryHandler;
import com.pragma.emazon.stock_microservice.application.handler.IListCategoriesHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.CategoryApiResponses.*;
import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.CategoryTag.*;
import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.PageResponse.*;
import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.ResponseCode.*;

@RestController
@RequestMapping("/categories/")
@Tag(name = TAG_NAME, description = TAG_DESCRIPTION)
@RequiredArgsConstructor
@Validated
public class CategoryRestController {
    private final ICreateCategoryHandler createCategoryHandler;
    private final IListCategoriesHandler listCategoriesHandler;

    @Operation(summary = SUMMARY_CREATE_CATEGORY, description = DESCRIPTION_CREATE_CATEGORY)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_201, description = DESCRIPTION_201, content = @Content),
        @ApiResponse(responseCode = CODE_409, description = DESCRIPTION_409, content = @Content),
        @ApiResponse(responseCode = CODE_400, description = DESCRIPTION_400, content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        createCategoryHandler.createCategory(createCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = SUMMARY_LIST_CATEGORIES, description = DESCRIPTION_LIST_CATEGORIES)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_200, description = DESCRIPTION_200, content = @Content),
        @ApiResponse(responseCode = CODE_400, description = DESCRIPTION_400, content = @Content),
        @ApiResponse(responseCode = CODE_404, description = DESCRIPTION_404, content = @Content)
    })
    @GetMapping
    public ResponseEntity<PaginatedResponse<CategoryResponse>> listCategories(
            @RequestParam(defaultValue = DEFAULT_PAGE_VALUE) @Positive(message = INVALID_PAGE_VALUE) @Min(value = 1, message = INVALID_PAGE_VALUE) Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_VALUE) @Positive(message = INVALID_PAGE_ITEMS_NUMBER ) @Min(value = 1, message = INVALID_PAGE_ITEMS_NUMBER) Integer size,
            @RequestParam(defaultValue = DEFAULT_SORT_DIRECTION_VALUE) @Pattern(regexp = "(?i)^(asc|desc)$", message = INVALID_SORT_DIRECTION) String direction) {

        PaginatedResponse<CategoryResponse> categoryResponseList = listCategoriesHandler.listCategories(page, size, direction);

        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseList);
    }
}
