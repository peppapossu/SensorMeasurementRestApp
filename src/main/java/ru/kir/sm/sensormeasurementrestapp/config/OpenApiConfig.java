package ru.kir.sm.sensormeasurementrestapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sensor Measurement REST API")
                        .version("1.0.0")
                        .description("Sensor Measurement REST API"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer configuration"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                        new SecurityScheme()
                                .name("Bearer")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .description("Enter Bearer Token: Bearer <token>")
                        )
                );
    }
}
