package com.knoflik.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/room").setViewName("room");
        registry.addViewController("/room-creation")
                .setViewName("room-creation");
        registry.addViewController("/registration").setViewName("registration");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/js/**",
                        "/html/**", "/css/**", "/images/**")
                .addResourceLocations("classpath:/static/js/",
                        "classpath:/", "classpath:/static/css/",
                        "classpath:/static/images/");
    }

}
