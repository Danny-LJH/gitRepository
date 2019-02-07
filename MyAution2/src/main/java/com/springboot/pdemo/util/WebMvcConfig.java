package com.springboot.pdemo.util;

import com.springboot.pdemo.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private FileUploadProperties fileUploadProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileUploadProperties.getStaticAccessPath()).addResourceLocations("file:"+fileUploadProperties.getFileUploadFloder() + "/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathes=new ArrayList<>();
        pathes.add("/user/**");
        pathes.add("/css/**");
        pathes.add("/js/**");
        pathes.add("/images/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(pathes);
    }
}
