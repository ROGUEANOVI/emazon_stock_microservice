package com.pragma.emazon.stock_microservice.application.handler;

import com.pragma.emazon.stock_microservice.application.dto.request.CreateArticleRequest;
import com.pragma.emazon.stock_microservice.application.mapper.ArticleRequestMapper;
import com.pragma.emazon.stock_microservice.domain.port.api.ICreateArticleServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateArticleHandler implements ICreateArticleHandler {

    private final ICreateArticleServicePort createArticleServicePort;
    private final ArticleRequestMapper articleRequestMapper;

    @Override
    public void createArticle(CreateArticleRequest createArticleRequest) {
        createArticleServicePort.createArticle(articleRequestMapper.toArticle(createArticleRequest));
    }
}
