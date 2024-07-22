package com.medilabo.front.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "medilabo")
public class CustomProperties {
    private String gatewayURL;

    public String getGatewayURL() {
        return gatewayURL;
    }
    public void setGatewayURL(String gatewayURL) {
        this.gatewayURL = gatewayURL;
    }
}
