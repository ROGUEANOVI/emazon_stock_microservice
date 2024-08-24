package com.pragma.emazon.stock_microservice.domain.port.spi;

public interface IExistsCategoryByNamePersistencePort {
    boolean existsCategoryByName(String name);
}
