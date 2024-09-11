package com.medilabo.gateway.service;

import com.medilabo.gateway.repository.CustomProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class MyGateway {
    private String infoURL;
    private String notesURL;
    private String riskURL;

    public MyGateway(CustomProperties props) {
        this.infoURL = props.getInfoURL();
        this.notesURL = props.getNotesURL();
        this.riskURL = props.getRiskURL();
    }

    @Bean
    public RouterFunction<ServerResponse> getPatientRoute() {
        return route().GET("/patient", http(infoURL)).build();
    }
    @Bean
    public RouterFunction<ServerResponse> getPatientAlternativeRoutes() {
        return route().GET("/patient/*", http(infoURL)).build();
    }
    @Bean
    public RouterFunction<ServerResponse> postPatientRoute() {
        return route().POST("/patient", http(infoURL)).build();
    }
    @Bean
    public RouterFunction<ServerResponse> putPatientRoute() {
        return route().PUT("/patient", http(infoURL)).build();
    }
    @Bean
    public RouterFunction<ServerResponse> deletePatientRoute() {
        return route().DELETE("/patient", http(infoURL)).build();
    }

    @Bean
    public RouterFunction<ServerResponse> getNotesRoute() {
        return route().GET("/note", http(notesURL)).build();
    }
    @Bean
    public RouterFunction<ServerResponse> postNoteRoute() {
        return route().POST("/note", http(notesURL)).build();
    }

    @Bean
    public RouterFunction<ServerResponse> getRiskRoute() {
        return route().GET("/risk", http(riskURL)).build();
    }
}
