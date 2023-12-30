package com.dekandev.ip.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Open IP API")
                        .version("1.0")
                        .description("""
                                This is an api provides continent, country and city
                                information based on a provided ip address
                                for last 30 minutes
                                """));
    }

}
