package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.response.ArticleResponse;
import com.pragma.emazon.stock_microservice.application.dto.response.PaginatedResponse;
import com.pragma.emazon.stock_microservice.application.mapper.PaginatedResponseMapper;
import com.pragma.emazon.stock_microservice.domain.port.api.IListArticlesServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ListArticlesHandler implements IListArticlesHandler {

    private final IListArticlesServicePort listArticlesServicePort;
    private final PaginatedResponseMapper paginatedResponseMapper;

    @Override
    public PaginatedResponse<ArticleResponse> listArticles(Integer page, Integer size, String direction, String sortBy) {
        return paginatedResponseMapper.toArticlesPaginatedResponse(listArticlesServicePort.listArticles(page, size, direction, sortBy));
    }
}
