package com.project.toystore.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@ComponentScan("com.project.toystore.configuration")
public class PathConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/vendor/**").addResourceLocations("/resources/vendor/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images");
        registry.addResourceHandler("Frontend/css/**").addResourceLocations("/Frontend/resources/css");
        registry.addResourceHandler("Frontend/js/**").addResourceLocations("/Frontend/resources/js");
        registry.addResourceHandler("Frontend/fonts/**").addResourceLocations("/Frontend/resources/fonts");
        registry.addResourceHandler("Frontend/scss/**").addResourceLocations("/Frontend/resources/scss");

    }
}
