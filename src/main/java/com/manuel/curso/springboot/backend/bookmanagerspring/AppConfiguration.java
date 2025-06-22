package com.manuel.curso.springboot.backend.bookmanagerspring;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
public class AppConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**") // Para todas las rutas
                .allowedOrigins("*") // De todos los orígenes
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos aceptados
                .allowedHeaders("*"); // Cabeceras permitidas

    }
}
