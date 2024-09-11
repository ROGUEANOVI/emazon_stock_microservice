package com.pragma.emazon.stock_microservice.infrastructure.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pragma.emazon.stock_microservice.infrastructure.constant.OpenApiMessages.*;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(){

        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes(SECURITY_SCHEME_KEY, new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(SECURITY_SCHEME)
                        .bearerFormat(BEARER_FORMAT)
                )
            )
            .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_KEY))
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