package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import com.pragma.emazon.stock_microservice.application.dto.request.UpdateQuantityRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.mapper.ArticleRequestMapper;
import com.pragma.emazon.stock_microservice.application.mapper.PaginatedResponseMapper;
import com.pragma.emazon.stock_microservice.domain.constant.ArticleValidationMessages;
import com.pragma.emazon.stock_microservice.domain.port.api.IArticleServicePort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;
    private final PaginatedResponseMapper paginatedResponseMapper;

    @Override
    public void createArticle(@Valid CreateArticleRequest createArticleRequest) {

        articleServicePort.createArticle(articleRequestMapper.toArticle(createArticleRequest));
    }

    @Override
    public PaginatedResponse<ArticleResponse> listArticles(Integer page, Integer size, String direction, String sortBy) {

        return paginatedResponseMapper.toArticlesPaginatedResponse(articleServicePort.listArticles(page, size, direction, sortBy));
    }

    @Override
    public void updateArticleQuantity(
        @Min(value = ArticleValidationMessages.MINIMUM_ARTICLE_ID_VALUE,message = ArticleValidationMessages.INVALID_ARTICLE_ID)
        @NotNull(message = ArticleValidationMessages.INVALID_ARTICLE_ID)
        Long articleId,
        @Valid UpdateQuantityRequest updateQuantityRequest) {

        articleServicePort.updateArticleQuantity(articleId, updateQuantityRequest.getQuantity());
    }
}
