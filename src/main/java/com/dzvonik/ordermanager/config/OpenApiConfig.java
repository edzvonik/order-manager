package com.dzvonik.ordermanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                description = "Order Manager API", version = "1.0.0",
                contact = @Contact(
                        name = "Order Manager on GitHub",
                        url = "https://github.com/edzvonik/order-manager"
                )
        )
)
public class OpenApiConfig {

}
