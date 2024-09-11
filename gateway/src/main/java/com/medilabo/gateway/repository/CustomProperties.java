package com.medilabo.gateway.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "medilabo")
public class CustomProperties {
    private String infoURL;
    private String notesURL;
    private String riskURL;

    public String getInfoURL() {
        return infoURL;
    }

    public void setInfoURL(String infoURL) {
        this.infoURL = infoURL;
    }

    public String getNotesURL() {
        return notesURL;
    }

    public void setNotesURL(String notesURL) {
        this.notesURL = notesURL;
    }

    public String getRiskURL() {
        return riskURL;
    }

    public void setRiskURL(String riskURL) {
        this.riskURL = riskURL;
    }
}
