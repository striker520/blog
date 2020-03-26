package com.fkzm.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name="spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource",matchIfMissing = true)
@Configuration
public class DruidDataSourceConfiguration {

    @ConfigurationProperties("spring.datasource")
    @Bean
    DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }


    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewer(){
        ServletRegistrationBean<StatViewServlet> bean=new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*" );
        Map<String,String> initParams=new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","1323");
        initParams.put("allow","");
        bean.setInitParameters(initParams);
        return bean;

    }
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        FilterRegistrationBean<WebStatFilter> filter=new FilterRegistrationBean<>();
        filter.setFilter(new WebStatFilter());
        Map<String,String> initParams=new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");

        filter.setInitParameters(initParams);
        filter.setUrlPatterns(Collections.singletonList("/*"));

        return filter;
    }




}
