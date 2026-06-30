package com.perfulandia.gatewayservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GatewayConfigTest {

    @Autowired
    private RouteLocator routeLocator;

    @Test
    void deberiaCargarTodasLasRutasDelGateway() {
        StepVerifier.create(
                routeLocator.getRoutes()
                        .map(route -> route.getId())
                        .collectList()
        )
        .assertNext(rutas -> {
            assertTrue(rutas.contains("soporte-service"));
            assertTrue(rutas.contains("pedido-service"));

            // Ajusta estos nombres según los IDs reales que pusiste en GatewayConfig.java
            assertTrue(rutas.contains("tiendas-service"));
            assertTrue(rutas.contains("inventario-catalogo-service"));
            assertTrue(rutas.contains("resena-service"));
        })
        .verifyComplete();
    }

    @Test
    void deberiaTenerCantidadCorrectaDeRutas() {
        StepVerifier.create(
                routeLocator.getRoutes()
                        .map(route -> route.getId())
                        .collectList()
        )
        .assertNext(rutas -> {
            assertEquals(5, rutas.size());
        })
        .verifyComplete();
    }

    @Test
    void deberiaCargarRutaSoporteService() {
        StepVerifier.create(
                routeLocator.getRoutes()
                        .filter(route -> route.getId().equals("soporte-service"))
                        .collectList()
        )
        .assertNext(rutas -> {
            assertEquals(1, rutas.size());
            assertEquals("soporte-service", rutas.get(0).getId());
        })
        .verifyComplete();
    }

    @Test
    void deberiaCargarRutaPedidoService() {
        StepVerifier.create(
                routeLocator.getRoutes()
                        .filter(route -> route.getId().equals("pedido-service"))
                        .collectList()
        )
        .assertNext(rutas -> {
            assertEquals(1, rutas.size());
            assertEquals("pedido-service", rutas.get(0).getId());
        })
        .verifyComplete();
    }

    @Test
    void noDeberiaExistirRutaInvalida() {
        StepVerifier.create(
                routeLocator.getRoutes()
                        .map(route -> route.getId())
                        .collectList()
        )
        .assertNext(rutas -> {
            assertTrue(!rutas.contains("servicio-inexistente"));
        })
        .verifyComplete();
    }
}