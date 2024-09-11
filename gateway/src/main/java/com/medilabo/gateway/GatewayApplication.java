package com.medilabo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.medilabo.gateway.config","com.medilabo.gateway.controller","com.medilabo.gateway.model","com.medilabo.gateway.service","com.medilabo.gateway.util","com.medilabo.gateway.repository"})
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
