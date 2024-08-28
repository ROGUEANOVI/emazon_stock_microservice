package com.pragma.emazon.stock_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.stock_microservice.domain.port.spi.IExistsArticleByNamePersistencePort;
import com.pragma.emazon.stock_microservice.infrastructure.out.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistsArticleByNameJpaAdapter implements IExistsArticleByNamePersistencePort {

    private final IArticleRepository articleRepository;

    @Override
    public Boolean existsArticleByName(String name) {

        return articleRepository.existsByName(name);
    }
}
