package com.wounom.kaoyaniep.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/31 11:02
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                //.allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowedMethods("POST","GET","OPTIONS","DELETE")
                .maxAge(168000)
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}