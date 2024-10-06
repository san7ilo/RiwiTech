package com.riwi.RiwiTech.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Riwi Tech",
        version = "1.0",
        description = "API for Riwi Tech"
),
        servers = {
                //@Server(url = "https://api.herotraining.com/v1", description = "Server of production"),
                @Server(url = "http://localhost:8080", description = "Server local")
        }
)
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(){

        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .name("Authorization")));

    }
}
