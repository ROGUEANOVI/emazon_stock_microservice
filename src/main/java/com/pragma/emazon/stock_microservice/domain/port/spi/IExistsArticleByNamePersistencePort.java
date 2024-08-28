package com.pragma.emazon.stock_microservice.domain.port.spi;

public interface IExistsArticleByNamePersistencePort {

    Boolean existsArticleByName(String name);
}
