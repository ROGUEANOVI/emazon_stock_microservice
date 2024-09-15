package com.pragma.emazon.stock_microservice.infrastructure.in.rest;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import com.pragma.emazon.stock_microservice.application.dto.request.UpdateQuantityRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.handler.IArticleHandler;
import com.pragma.emazon.stock_microservice.infrastructure.constant.PreAuthorizeMessages;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.pragma.emazon.stock_microservice.infrastructure.constant.ArticleApiMessages.*;
import static com.pragma.emazon.stock_microservice.infrastructure.constant.OpenApiMessages.*;
import static com.pragma.emazon.stock_microservice.infrastructure.constant.PageMessages.*;
import static com.pragma.emazon.stock_microservice.infrastructure.constant.PageMessages.INVALID_SORT_DIRECTION;
import static com.pragma.emazon.stock_microservice.infrastructure.constant.Regex.*;

@RestController
@RequestMapping(ROUTE_ARTICLES)
@Tag(name = TAG_NAME, description = TAG_DESCRIPTION)
@RequiredArgsConstructor
@Validated
public class ArticleRestController {

    private final IArticleHandler articleHandler;

    @Operation(summary = SUMMARY_CREATE_ARTICLE, description = DESCRIPTION_CREATE_ARTICLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_201, description = DESCRIPTION_201, content = @Content),
            @ApiResponse(responseCode = CODE_400, description = DESCRIPTION_400, content = @Content),
            @ApiResponse(responseCode = CODE_409, description = DESCRIPTION_409, content = @Content)
    })
    @PreAuthorize(PreAuthorizeMessages.HAS_ROLE_ADMIN)
    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody CreateArticleRequest createArticleRequest) {

        articleHandler.createArticle(createArticleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = SUMMARY_LIST_ARTICLES, description = DESCRIPTION_LIST_ARTICLES)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_200, description = DESCRIPTION_200, content = @Content),
            @ApiResponse(responseCode = CODE_400, description = DESCRIPTION_400, content = @Content),
            @ApiResponse(responseCode = CODE_404, description = DESCRIPTION_404, content = @Content)
    })
    @GetMapping
    public ResponseEntity<PaginatedResponse<ArticleResponse>> listArticles(
            @RequestParam(defaultValue = DEFAULT_PAGE_VALUE)  @Positive(message = INVALID_PAGE_VALUE) @Min(value = MINIMUM_PAGE_VALUE, message = INVALID_PAGE_VALUE) Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_VALUE) @Positive(message = INVALID_PAGE_ITEMS_NUMBER) @Min(value = MINIMUM_PAGE_SIZE_VALUE, message = INVALID_PAGE_ITEMS_NUMBER) Integer size,
            @RequestParam(defaultValue = DEFAULT_SORT_DIRECTION_VALUE) @Pattern(regexp = SORT_DIRECTION_REGEX, message = INVALID_SORT_DIRECTION) String direction,
            @RequestParam(defaultValue = DEFAULT_SORT_BY_VALUE) @Pattern(regexp = SORT_BY_REGEX, message = INVALID_SORT_BY) String sortBy
    ) {

        PaginatedResponse<ArticleResponse> articleResponseList = articleHandler.listArticles(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(articleResponseList);
    }

    @Operation(summary = SUMMARY_UPDATE_ARTICLE_QUANTITY, description = DESCRIPTION_UPDATE_ARTICLE_QUANTITY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_200, description = DESCRIPTION_200, content = @Content),
            @ApiResponse(responseCode = CODE_400, description = DESCRIPTION_400, content = @Content),
            @ApiResponse(responseCode = CODE_404, description = DESCRIPTION_404, content = @Content)
    })
    @PreAuthorize(PreAuthorizeMessages.HAS_ROLE_ROLE_WAREHOUSE_ASSISTANT)
    @PutMapping(ROUTE_ARTICLE_QUANTITY)
    public ResponseEntity<Void> updateArticleQuantity(@PathVariable(ID) Long articleId,
                                                      @RequestBody UpdateQuantityRequest updateQuantityRequest) {

        articleHandler.updateArticleQuantity(articleId, updateQuantityRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
