package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateCategoryRequest;
import com.pragma.emazon.stock_microservice.application.handler.ICreateCategoryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.CategoryApiResponses.*;
import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.CategoryTag.*;
import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.ResponseCode.*;

@RestController
@RequestMapping("/categories")
@Tag(name = TAG_NAME, description = TAG_DESCRIPTION)
@RequiredArgsConstructor
@Validated
public class CategoryRestController {
    private final ICreateCategoryHandler createCategoryHandler;

    @Operation(summary = OPERATION_SUMMARY, description = OPERATION_DESCRIPTION)
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
}
