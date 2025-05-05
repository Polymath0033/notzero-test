package com.polymath.notzerowalletservice.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI walletServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Wallet Service")
                        .description("API for managing wallet services")
                        .version("1.0.0"));
    }
}
