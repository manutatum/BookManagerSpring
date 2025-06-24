package com.manuel.curso.springboot.backend.bookmanagerspring.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor local de desarrollo")
        }
)
public class SwaggerConfiguration {

    @Bean
    public OpenAPI detailsOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Book Manager API")
                        .description("Documentación de la API para gestionar tu lectura")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Manuel Jiménez Bravo")
                                .email("jbmanuel16@gmail.com")
                                .url("https://github.com/manutatum")
                        )
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")
                        )
                );
    }

}
