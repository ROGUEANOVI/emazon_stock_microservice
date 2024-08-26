package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateBrandRequest;
import com.pragma.emazon.stock_microservice.application.handler.ICreateBrandHandler;
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

import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.BrandTag.*;
import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.BrandApiResponses.*;
import static com.pragma.emazon.stock_microservice.infrastructure.in.rest.constant.ResponseCode.*;

@RestController
@RequestMapping("/brands/")
@Tag(name = TAG_NAME, description = TAG_DESCRIPTION)
@RequiredArgsConstructor
@Validated
public class BrandRestController {
    private final ICreateBrandHandler createBrandHandler;

    @Operation(summary = SUMMARY_CREATE_BRAND, description = DESCRIPTION_CREATE_BRAND)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_201, description = DESCRIPTION_201, content = @Content),
            @ApiResponse(responseCode = CODE_409, description = DESCRIPTION_409, content = @Content),
            @ApiResponse(responseCode = CODE_400, description = DESCRIPTION_400, content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> createBrand(@RequestBody CreateBrandRequest createBrandRequest) {
        createBrandHandler.createBrand(createBrandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
