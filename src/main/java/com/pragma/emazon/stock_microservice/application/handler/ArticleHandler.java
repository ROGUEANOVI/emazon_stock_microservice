package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.mapper.ArticleRequestMapper;
import com.pragma.emazon.stock_microservice.application.mapper.PaginatedResponseMapper;
import com.pragma.emazon.stock_microservice.domain.port.api.IArticleServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final ArticleRequestMapper articleRequestMapper;
    private final PaginatedResponseMapper paginatedResponseMapper;

    @Override
    public void createArticle(CreateArticleRequest createArticleRequest) {

        articleServicePort.createArticle(articleRequestMapper.toArticle(createArticleRequest));
    }

    @Override
    public PaginatedResponse<ArticleResponse> listArticles(Integer page, Integer size, String direction, String sortBy) {

        return paginatedResponseMapper.toArticlesPaginatedResponse(articleServicePort.listArticles(page, size, direction, sortBy));
    }
}
