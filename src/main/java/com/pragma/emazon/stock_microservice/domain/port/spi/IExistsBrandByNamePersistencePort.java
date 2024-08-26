package com.pragma.emazon.stock_microservice.domain.port.spi;

public interface IExistsBrandByNamePersistencePort {
    Boolean existsBrandByName(String name);
}
