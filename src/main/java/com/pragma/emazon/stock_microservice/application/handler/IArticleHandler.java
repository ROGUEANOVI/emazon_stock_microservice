package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import com.pragma.emazon.stock_microservice.application.dto.request.UpdateQuantityRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.domain.constant.ArticleValidationMessages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public interface IArticleHandler {

    void createArticle(@Valid CreateArticleRequest createArticleRequest);

    PaginatedResponse<ArticleResponse> listArticles(Integer page, Integer size, String direction, String sortBy);

    void updateArticleQuantity(
            @Min(value = ArticleValidationMessages.MINIMUM_ARTICLE_ID_VALUE, message = ArticleValidationMessages.INVALID_ARTICLE_ID)
            @NotNull(message = ArticleValidationMessages.INVALID_ARTICLE_ID)
            Long articleId, @Valid UpdateQuantityRequest updateQuantityRequest);
}
