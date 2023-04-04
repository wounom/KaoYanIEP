package com.wounom.kaoyaniep.config;

import com.wounom.kaoyaniep.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 12:55
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
   @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/logout")
                .excludePathPatterns("/user/logout")
                .excludePathPatterns("/user/regist")
                .excludePathPatterns("/user/forgetpw")
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/firstpage/**")
                .excludePathPatterns("/images/firstpage/**");
    }
}