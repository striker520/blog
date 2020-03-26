package com.fkzm.blog.config;

import com.fkzm.blog.config.filter.LoginVerificationFilter;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@EnableAspectJAutoProxy()
@MapperScan("com.fkzm.blog.mapper")
@Configuration
public class MainConfig {

//            @Bean
//            SqlSessionFactoryBean sqlSessionFactoryBean(){
//                SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
//
//                return sqlSessionFactoryBean;
//
//            }
//    @Bean
//    FilterRegistrationBean<LoginVerificationFilter> filterRegistrationBean(){
//        FilterRegistrationBean<LoginVerificationFilter> filterRegistrationBean=new FilterRegistrationBean<>(new LoginVerificationFilter());
//        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/admin/**"));
//        filterRegistrationBean.
//        return filterRegistrationBean;
//    }
        @Bean
        public PageHelper pageHelper(){
            return new PageHelper();
        }


}
