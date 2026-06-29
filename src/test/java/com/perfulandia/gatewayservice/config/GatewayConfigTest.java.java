package com.perfulandia.gatewayservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;
import reactor.test.StepVerifier;

@SpringBootTest
class GatewayConfigTest {

    @Autowired
    private RouteLocator routeLocator;

    @Test
    void deberiaCargarRutaSoporteService() {
        StepVerifier.create(routeLocator.getRoutes())
                .expectNextMatches(route -> route.getId().equals("soporte-service"))
                .verifyComplete();
    }
}