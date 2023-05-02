package com.wounom.kaoyaniep.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 16:02
 */
@Configuration
public class fileConfig implements WebMvcConfigurer {
    private String imgPath="/www/wwwroot/JAVA/Project/IRCP/images/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/userheadimg/**").addResourceLocations("file:"+imgPath+"userheadimg/");
        registry.addResourceHandler("/images/firstpage/**").addResourceLocations("file:"+imgPath+"firstpage/");
        registry.addResourceHandler("/images/university/**").addResourceLocations("file:"+imgPath+"university/");
    }

}
