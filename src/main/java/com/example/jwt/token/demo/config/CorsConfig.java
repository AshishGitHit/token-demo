package com.example.jwt.token.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String DELETE = "DELETE";
    private static final String PUT = "PUT";

    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods(GET, POST, PUT, DELETE)
                        .allowedOriginPatterns("*")
                        .allowCredentials(true);
            }
        };
    }
}
