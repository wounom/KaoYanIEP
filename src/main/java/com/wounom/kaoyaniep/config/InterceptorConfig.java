package com.wounom.kaoyaniep.config;

import com.wounom.kaoyaniep.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
                .excludePathPatterns("/user/login","/user/regist","/user/forgetpw")
                .excludePathPatterns("/admin/login","/admin/newAdmin","/admin/resetadmin")
                .excludePathPatterns("/firstpage/**","/images/firstpage/**")
                .excludePathPatterns("/email/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v3/**","/v2/**", "/swagger-ui/**");
    }
    //todo:项目完成后加上拦截器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置拦截器访问静态资源
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}