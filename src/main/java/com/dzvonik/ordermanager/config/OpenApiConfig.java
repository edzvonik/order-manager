package com.dzvonik.ordermanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Order Manager API",
                description = "Order Manager", version = "1.0.0",
                contact = @Contact(
                        name = "Dzvonik Evgenii",
                        url = "https://github.com/edzvonik/order-manager"
                )
        )
)
public class OpenApiConfig {

}
