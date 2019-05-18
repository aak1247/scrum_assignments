package com.aak1247;


import com.aak1247.common.AbstractDBService;
import com.aak1247.common.DBServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    public static void main(String[] args) {

        // This is basically the same example as the web-examples static site example but it's booted using
        // Spring Boot, not Vert.x
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public AbstractDBService dBService() {
        return new DBServiceImpl();
    }
}
