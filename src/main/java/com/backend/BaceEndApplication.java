package com.backend;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@OpenAPIDefinition(
        info = @Info(
                title = "GOLD-SHOP project REST API Documentation",
                description = "GOLD-SHOP  application REST API Documentation and developed by Pruthviraj Patil",
                version="v1",
                contact = @Contact(
                        name = "Code-crafter",
                        email = "info@code-crafter.in",
                        url = "https://code-crafter.in"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "GOLD-SHOP REST API Documentation",
                url = "https://code-crafter.in"
        )
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BaceEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaceEndApplication.class, args);
    }

}
