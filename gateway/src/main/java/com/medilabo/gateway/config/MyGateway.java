package com.medilabo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class MyGateway {

    @Bean
    public RouterFunction<ServerResponse> getPatientRoute() {
        return route().GET("/patient", http("http://localhost:8081")).build();
    }
    @Bean
    public RouterFunction<ServerResponse> getPatientAlternativeRoutes() {
        return route().GET("/patient/*", http("http://localhost:8081")).build();
    }
    @Bean
    public RouterFunction<ServerResponse> postPatientRoute() {
        return route().POST("/patient", http("http://localhost:8081")).build();
    }
    @Bean
    public RouterFunction<ServerResponse> putPatientRoute() {
        return route().PUT("/patient", http("http://localhost:8081")).build();
    }
    @Bean
    public RouterFunction<ServerResponse> deletePatientRoute() {
        return route().DELETE("/patient", http("http://localhost:8081")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> getNotesRoute() {
        return route().GET("/note", http("http://localhost:8083")).build();
    }
    @Bean
    public RouterFunction<ServerResponse> postNoteRoute() {
        return route().POST("/note", http("http://localhost:8083")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> getRiskRoute() {
        return route().GET("/risk", http("http://localhost:8084")).build();
    }
}
