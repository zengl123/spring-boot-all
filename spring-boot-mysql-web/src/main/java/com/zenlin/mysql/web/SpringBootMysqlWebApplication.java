package com.zenlin.mysql.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.zenlin.mysql.web")
public class SpringBootMysqlWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMysqlWebApplication.class, args);
    }
}
