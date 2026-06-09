package com.marine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private FileUploadConfig fileUploadConfig;

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        String uploadPath = fileUploadConfig.getPath();
        if (!uploadPath.endsWith("/")) {
            uploadPath += "/";
        }
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + uploadPath);
    }
}
