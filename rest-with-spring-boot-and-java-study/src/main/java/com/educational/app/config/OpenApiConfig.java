package com.educational.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Studying REST API with Spring Boot")
                        .version("v1")
                        .description("Studying API Development with Spring Boot and many important tools.")
                        .termsOfService("not avaliable for now.")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("not avaliable for now.")
                        )
                );
    }
}
