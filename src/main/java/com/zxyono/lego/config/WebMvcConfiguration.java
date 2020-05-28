package com.zxyono.lego.config;

import com.zxyono.lego.util.AdminLoginMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Value("${lego.images.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+path);
    }

    /**
     * 维护一个全局的<adminId, token> Map
     * @return
     */
    @Bean
    public AdminLoginMap adminLoginMap() {
        return new AdminLoginMap();
    }

}