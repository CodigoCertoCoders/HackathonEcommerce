package com.guardians_of_the_code.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;

@OpenAPIDefinition(
        info = @Info(
                title = "Backend MalteX",
                description = "API RESTFull para MalteX",
                version = "1.0.0",
                contact = @Contact(name = "Joca",email = "namibo654@gmail.com")
        ),
        servers = {
                @Server(
                        url = "https://maltex-back-production.up.railway.app/",
                        description = "Produção"
                ),
                @Server(
                        url = "http://localhost:8080/",
                        description = "Local"
                )
        },
        security = @SecurityRequirement(name = "bearerAuth")
)
@Configuration
public class SwaggerConfig {

}
