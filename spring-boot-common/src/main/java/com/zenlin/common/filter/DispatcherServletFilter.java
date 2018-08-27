package com.zenlin.common.filter;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.common.filter
 * @ClassName DispatcherServlterFilter
 * @Author ZENLIN
 * @Date 2018-07-03 22:21
 * @Description TODO
 * @Version
 * @Modified By
 */
@Configuration
@ComponentScan(basePackages = "com.zenlin", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = RestController.class)})
public class DispatcherServletFilter implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }
}
