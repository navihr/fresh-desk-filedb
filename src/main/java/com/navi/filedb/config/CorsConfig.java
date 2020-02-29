package com.navi.filedb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${filedb.corsConfig.allowedOrgin}")
    private String[] allowedOrgin;

    @Value("${filedb.corsConfig.allowedMethods}")
    private String[] allowedMethods;

    @Value("${filedb.corsConfig.allowedCredentials}")
    private boolean allowCredentials;

    @Value("${filedb.corsConfig.maxAge}")
    private long maxAge;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrgin)
                .allowedMethods(allowedMethods)
                .allowCredentials(allowCredentials)
                .maxAge(maxAge);
    }

}
