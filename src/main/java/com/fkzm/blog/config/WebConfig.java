package com.fkzm.blog.config;

import com.fkzm.blog.config.filter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginInterceptor());
        interceptorRegistration.addPathPatterns("/admin/**");
        interceptorRegistration.excludePathPatterns("/admin","/admin/login");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/static/**");
    }

}
