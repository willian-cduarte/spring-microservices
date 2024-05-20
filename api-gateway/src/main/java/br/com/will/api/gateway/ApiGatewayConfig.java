package br.com.will.api.gateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Predicate;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        Function<PredicateSpec, Buildable<Route>> function =
                p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("hello", "world")
                                .addRequestParameter("hello", "world")
                                )
                        .uri("http://httpbin.org:80");

        return builder.routes()
                .route(function)
                .route(p -> p
                        .path("/exchange-service/**")
                        .uri("lb://exchange-service"))
                .route(p -> p
                        .path("/book-service/**")
                        .uri("lb://book-service"))
                .build()
                ;
    }
}
