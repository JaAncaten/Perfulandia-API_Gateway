package com.perfulandia.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator rutas(RouteLocatorBuilder builder) {
        return builder.routes()

               .route("soporte-service", r -> r
                    .path("/api/soporte/**")
                    .filters(f -> f.rewritePath("/api/soporte/(?<segment>.*)", "/api/${segment}"))
                    .uri("http://localhost:8070"))

                .build();
    }
}