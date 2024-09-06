package com.guardians_of_the_code.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://malte-x.vercel.app", "http://localhost:8080","https://maltex-back-production.up.railway.app/","https://maltex-back-production.up.railway.app/swagger-ui/index.html","http://localhost:8080/swagger-ui/index.html")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
