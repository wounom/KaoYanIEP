package com.wounom.kaoyaniep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.TemplateEngine;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableTransactionManagement
@EnableOpenApi
@MapperScan("com.wounom.kaoyaniep.dao")
public class KaoYanIepApplication {
    public static void main(String[] args) {
        SpringApplication.run(KaoYanIepApplication.class, args);
    }
}
