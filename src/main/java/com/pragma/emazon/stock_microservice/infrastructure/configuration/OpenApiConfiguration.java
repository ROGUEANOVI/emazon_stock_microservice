package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pragma.emazon.stock_microservice.infrastructure.constant.OpenApiInformation.*;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(){

        return new OpenAPI()
            .components(new Components())
            .info(
                new Info()
                    .title(TITLE)
                    .description(DESCRIPTION)
                    .version(VERSION)
                    .contact(new Contact()
                        .name(CONTACT_NAME)
                        .email(CONTACT_EMAIL)
                        .url(CONTACT_URL)
                    )
            )
            .externalDocs(
                new ExternalDocumentation()
                    .description(EXTERNAL_DOCUMENT_DESCRIPTION)
                    .url(REPOSITORY_URL)
            );
    }
}