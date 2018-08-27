package com.zenlin.web.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.zenlin")
public class SpringBootWebWarApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebWarApplication.class, args);
    }
}
