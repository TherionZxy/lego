package com.zxyono.lego.config;

import com.zxyono.lego.controller.MyErrorController;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义异常处理配置类
 */
@Configuration
public class MyErrorControllerConfig {

    private ServerProperties serverProperties;

    public MyErrorControllerConfig(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes(this.serverProperties.getError().isIncludeException());
    }

    @Bean
    public ErrorController errorController(ErrorAttributes errorAttributes) {
        return new MyErrorController(errorAttributes, this.serverProperties.getError().isIncludeException());
    }
}